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
import app.persistence.entity.transactions.Transaction;
import app.persistence.repository.transactions.TransactionRepository;
import app.service.TransactionService;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final LoggerService loggerService;
    private final TransactionRepository transactionRepository;
    private final CrudRepositoryHelper<Transaction, TransactionRepository> crudRepositoryHelper;

    public TransactionServiceImpl(LoggerService loggerService, TransactionRepository transactionRepository, CrudRepositoryHelper<Transaction, TransactionRepository> crudRepositoryHelper) {
        this.loggerService = loggerService;
        this.transactionRepository = transactionRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Transaction entity) {
        crudRepositoryHelper.create(transactionRepository, entity);
        loggerService.commit(LoggerLevel.INFO, entity.getId() + " transaction created");
    }

    @Override
    public void update(Transaction entity) {
        crudRepositoryHelper.update(transactionRepository, entity);
        loggerService.commit(LoggerLevel.INFO, entity.getId() + " transaction updated");
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(transactionRepository, id);
        loggerService.commit(LoggerLevel.INFO, id + " transaction deleted");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Transaction> findById(Long id) {
        return crudRepositoryHelper.findById(transactionRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Transaction> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(transactionRepository, request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}
