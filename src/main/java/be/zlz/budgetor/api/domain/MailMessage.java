package be.zlz.budgetor.api.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Frederik on 24/06/2017.
 */
@Entity
public class MailMessage {

    @Id
    private long id;

    @NotEmpty
    private Date dateToSend;

    @NotEmpty
    private Date dateSent;

    private boolean sent;

    @NotBlank
    private String subject;

    @NotBlank
    private String message;

    public MailMessage(Date dateToSend, String subject, String message) {
        this.dateToSend = dateToSend;
        this.subject = subject;
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public Date getDateToSend() {
        return dateToSend;
    }

    public void setDateToSend(Date dateToSend) {
        this.dateToSend = dateToSend;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    @Override
    public String toString() {
        return "MailMessage{" +
                "id=" + id +
                ", dateToSend=" + dateToSend +
                ", dateSent=" + dateSent +
                ", sent=" + sent +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
