package be.zlz.budgetor.api.repository;

import be.zlz.budgetor.api.domain.TransactionRule;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRuleRepository extends CrudRepository<TransactionRule, Long> {
}
