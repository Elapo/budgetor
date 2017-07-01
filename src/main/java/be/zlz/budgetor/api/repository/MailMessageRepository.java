package be.zlz.budgetor.api.repository;

import be.zlz.budgetor.api.domain.MailMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MailMessageRepository extends CrudRepository<MailMessage, Long> {

    @Query("SELECT m from MailMessage m where m.sent=false")
    List<MailMessage> findAllUnsent();
}
