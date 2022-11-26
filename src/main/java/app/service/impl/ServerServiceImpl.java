package app.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import app.logger.LoggerLevel;
import app.logger.LoggerService;
import app.persistence.crud.CrudRepositoryHelper;
import app.persistence.datatable.DataTableRequest;
import app.persistence.datatable.DataTableResponse;
import app.persistence.entity.provider.Provider;
import app.persistence.entity.server.Server;
import app.persistence.entity.user.Personal;
import app.persistence.repository.server.ServerRepository;
import app.persistence.repository.user.PersonalRepository;
import app.service.ProviderService;
import app.service.ServerService;

import java.util.List;
import java.util.Optional;

@Service
public class ServerServiceImpl implements ServerService {

    private final LoggerService loggerService;
    private final ServerRepository serverRepository;
    private final ProviderService providerService;
    private final PersonalRepository personalRepository;
    private final CrudRepositoryHelper<Personal, PersonalRepository> crudRepositoryHelperPersonal;
    private final CrudRepositoryHelper<Server, ServerRepository> crudRepositoryHelper;

    public ServerServiceImpl(LoggerService loggerService, ServerRepository serverRepository, ProviderService providerService, PersonalRepository personalRepository, CrudRepositoryHelper<Personal, PersonalRepository> crudRepositoryHelperPersonal, CrudRepositoryHelper<Server, ServerRepository> crudRepositoryHelper) {
        this.loggerService = loggerService;
        this.serverRepository = serverRepository;
        this.providerService = providerService;
        this.personalRepository = personalRepository;
        this.crudRepositoryHelperPersonal = crudRepositoryHelperPersonal;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Server entity) {
        Provider provider = entity.getProvider();
        provider.addServer(entity);
        providerService.update(provider);
        loggerService.commit(LoggerLevel.INFO, entity.getServerName() + " server created");
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Server entity) {
        crudRepositoryHelper.update(serverRepository, entity);
        loggerService.commit(LoggerLevel.INFO, entity.getServerName() + " server updated");
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        if (findById(id).get().getPersonal() != null) {
            System.out.println(findById(id).get() + "triggered user update");
            Personal personal = findById(id).get().getPersonal();
            personal.removeRented(findById(id).get());
            crudRepositoryHelperPersonal.update(personalRepository, personal);
        }
        System.out.println(findById(id).get() + "deleted");
        crudRepositoryHelper.delete(serverRepository, id);
        loggerService.commit(LoggerLevel.INFO, id + " server deleted");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Server> findById(Long id) {
        return crudRepositoryHelper.findById(serverRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Server> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(serverRepository, request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Server> findAll() {
        return serverRepository.findAll();
    }
}
