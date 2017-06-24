package be.zlz.budgetor.api.repository;

import be.zlz.budgetor.api.domain.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByUserId(Long id);
}
