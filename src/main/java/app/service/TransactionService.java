package app.service;

import app.persistence.entity.transactions.Transaction;

import java.util.List;

public interface TransactionService extends BaseCrudService<Transaction> {

    List<Transaction> findAll();
}
