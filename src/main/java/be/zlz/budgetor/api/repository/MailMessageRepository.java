package be.zlz.budgetor.api.repository;

import be.zlz.budgetor.api.domain.MailMessage;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Frederik on 24/06/2017.
 */
public interface MailMessageRepository extends CrudRepository<MailMessage, Long> {
}
