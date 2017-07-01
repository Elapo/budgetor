package be.zlz.budgetor.api.domain;

import javax.persistence.*;

@Entity
public class TransactionRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private TransactionType type;

    private int amount;

    private String name;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private Account from;

    @ManyToOne
    private Account to;

    public TransactionRule() {
    }

    public TransactionRule(TransactionType type, int amount, Account from, Account to, String name, String description) {
        this.type = type;
        this.amount = amount;
        this.from = from;
        this.to = to;
        this.name = name;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "TransactionRule{" +
                "id=" + id +
                ", type=" + type +
                ", amount=" + amount +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", from=" + from.getNickName() +
                '}';
    }
}
