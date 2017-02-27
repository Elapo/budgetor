package be.zlz.budgeteer.api.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import java.util.ArrayList;

public class SavingsAccount {

    @GeneratedValue
    private long id;

    private long currentValue;

    @OneToMany
    ArrayList<Transaction> transactions;

    @OneToMany
    ArrayList<TransactionRule> autoRules;
}
