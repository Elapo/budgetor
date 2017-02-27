package be.zlz.budgeteer.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;

@Entity
public class Transaction {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long amount;

    private LocalDate timeStamp;
}
