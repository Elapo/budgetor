package be.zlz.budgetor.api.domain;

import javax.persistence.*;

@Entity
public class TransactionRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private TransactionType type;

    private int amount;

    @OneToOne
    private Account from;

    public TransactionRule() {
    }

    public TransactionRule(TransactionType type, int amount, Account from) {
        this.type = type;
        this.amount = amount;
        this.from = from;
    }

    public long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "TransactionRule{" +
                "id=" + id +
                ", type=" + type +
                ", amount=" + amount +
                ", from=" + from +
                '}';
    }
}
