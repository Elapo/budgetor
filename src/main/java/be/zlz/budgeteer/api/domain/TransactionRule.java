package be.zlz.budgeteer.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

enum Type{
    PERCENTUAL_INCOME,
    FLAT_INCOME,
    PERCENTUAL_PAYMENT,
    FLAT_PAYMENT,
}

@Entity
public class TransactionRule {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Type type;

    private int amount;

    @OneToOne
    private SavingsAccount from;
}
