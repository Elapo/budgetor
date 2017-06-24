package be.zlz.budgetor.api.repository;

import be.zlz.budgetor.api.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByEmailAddress(String email);
}
