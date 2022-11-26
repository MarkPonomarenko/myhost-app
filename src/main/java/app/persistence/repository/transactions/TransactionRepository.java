package app.persistence.repository.transactions;

import org.springframework.stereotype.Repository;
import app.persistence.entity.transactions.Transaction;
import app.persistence.repository.BaseRepository;

import java.util.List;

@Repository
public interface TransactionRepository extends BaseRepository<Transaction> {

    List<Transaction> findByEmail(String email);
}
