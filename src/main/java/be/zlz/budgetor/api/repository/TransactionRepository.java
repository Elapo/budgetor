package be.zlz.budgetor.api.repository;

import be.zlz.budgetor.api.domain.Account;
import be.zlz.budgetor.api.domain.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findByAccount(Account account);
}
