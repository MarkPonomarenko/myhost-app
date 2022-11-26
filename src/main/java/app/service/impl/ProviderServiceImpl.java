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
import app.persistence.repository.server.ProviderRepository;
import app.service.ProviderService;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final LoggerService loggerService;
    private final ProviderRepository providerRepository;
    private final CrudRepositoryHelper<Provider, ProviderRepository> crudRepositoryHelper;

    public ProviderServiceImpl(LoggerService loggerService, ProviderRepository providerRepository, CrudRepositoryHelper<Provider, ProviderRepository> crudRepositoryHelper) {
        this.loggerService = loggerService;
        this.providerRepository = providerRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Provider entity) {
        crudRepositoryHelper.create(providerRepository, entity);
        loggerService.commit(LoggerLevel.INFO, entity.getName() + " provider created");
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Provider entity) {
        crudRepositoryHelper.update(providerRepository, entity);
        loggerService.commit(LoggerLevel.INFO, entity.getName() + " provider updated");
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(providerRepository, id);
        loggerService.commit(LoggerLevel.INFO, id + " provider deleted");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Provider> findById(Long id) {
        return crudRepositoryHelper.findById(providerRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Provider> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(providerRepository, request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Provider> findAll() {
        return providerRepository.findAll();
    }
}
