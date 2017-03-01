package be.zlz.budgeteer.api.Service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MailService {

    @JmsListener(destination = "mail", containerFactory = "messageFactory")
    public void getEmailMessages(String email) {
        System.out.println("email = " + email);
    }
}
