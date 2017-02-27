package be.zlz.budgeteer.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import java.util.ArrayList;

@Entity
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    ArrayList<SavingsAccount> savings;

    private long currentValue;
}
