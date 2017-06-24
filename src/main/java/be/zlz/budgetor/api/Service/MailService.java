package be.zlz.budgetor.api.Service;

import be.zlz.budgetor.api.templates.MailTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MailService {

    private MailSender sender;

    private Logger logger;

    public MailService(){
        sender = new JavaMailSenderImpl();
        logger = Logger.getLogger(MailService.class);
    }

    @JmsListener(destination = "mail", containerFactory = "messageFactory")
    public void getEmailMessages(String email) {
        System.out.println("email = " + email);
    }

    private void sendMail(String address, MailTemplate template){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(address);
        message.setSubject(template.getSubject());
        message.setText(template.getMessage());

        try {
            sender.send(message);
        } catch (MailException ex){
            logger.error("Failed to send message to " + address, ex);
        }

    }
}
