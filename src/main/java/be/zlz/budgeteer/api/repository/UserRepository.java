package be.zlz.budgeteer.api.repository;

import be.zlz.budgeteer.api.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByEmailAddress(String email);
}
