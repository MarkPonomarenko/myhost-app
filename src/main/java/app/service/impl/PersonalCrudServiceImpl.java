package app.service.impl;

import app.exception.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import app.exception.EntityExistException;
import app.logger.LoggerLevel;
import app.logger.LoggerService;
import app.persistence.crud.CrudRepositoryHelper;
import app.persistence.datatable.DataTableRequest;
import app.persistence.datatable.DataTableResponse;
import app.persistence.entity.server.Server;
import app.persistence.entity.user.Personal;
import app.persistence.repository.server.ServerRepository;
import app.persistence.repository.user.PersonalRepository;
import app.service.PersonalCrudService;
import app.service.ServerService;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalCrudServiceImpl implements PersonalCrudService {

    private final LoggerService loggerService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PersonalRepository personalRepository;
    private final ServerRepository serverRepository;
    private final CrudRepositoryHelper<Server, ServerRepository> crudRepositoryHelperServer;
    private final CrudRepositoryHelper<Personal, PersonalRepository> crudRepositoryHelper;

    public PersonalCrudServiceImpl(
            LoggerService loggerService, BCryptPasswordEncoder bCryptPasswordEncoder,
            PersonalRepository personalRepository, ServerService serverService, ServerRepository serverRepository, CrudRepositoryHelper<Personal, PersonalRepository> crudRepositoryHelper, CrudRepositoryHelper<Server, ServerRepository> crudRepositoryHelperServer) {
        this.loggerService = loggerService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.personalRepository = personalRepository;
        this.serverRepository = serverRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.crudRepositoryHelperServer = crudRepositoryHelperServer;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(Personal personal) {
        if (personalRepository.existsByEmail(personal.getEmail())) {
            loggerService.commit(LoggerLevel.WARN, personal.getEmail() + " personal creation failed");
            throw new EntityExistException("this personal exists");
        }
        personal.setPassword(bCryptPasswordEncoder.encode(personal.getPassword()));
        crudRepositoryHelper.create(personalRepository, personal);
        loggerService.commit(LoggerLevel.INFO, personal.getEmail() + " created");
    }

    @Override
    public void update(Personal personal) {
        if (!personalRepository.existsByEmail(personal.getEmail())) {
            loggerService.commit(LoggerLevel.WARN, personal.getEmail() + " personal update failed");
            throw new EntityNotFoundException("this personal doesn't exist");
        }
        Personal updater = personalRepository.findByEmail(personal.getEmail());
        updater.setLastName(personal.getLastName());
        updater.setFirstName(personal.getFirstName());
        updater.setBalance(personal.getBalance());
        updater.setRented(personal.getRented());
        crudRepositoryHelper.update(personalRepository, personal);
        loggerService.commit(LoggerLevel.INFO, personal.getEmail() + " updated");
    }

    @Override
    public void delete(Long id) {
        for (Server server : findById(id).get().getRented()) {
            server.setPersonal(null);
            server.setVisible(true);
            crudRepositoryHelperServer.update(serverRepository, server);
        }
        crudRepositoryHelper.delete(personalRepository, id);
        loggerService.commit(LoggerLevel.INFO, id + " personal deleted");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Personal> findById(Long id) {
        return crudRepositoryHelper.findById(personalRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Personal> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(personalRepository, request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Personal> findAll() {
        return personalRepository.findAll();
    }


}
