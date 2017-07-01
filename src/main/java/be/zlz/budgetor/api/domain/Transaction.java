package be.zlz.budgetor.api.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long amount;

    private LocalDate timeStamp;

    @ManyToOne
    private TransactionRule createdBy;

    @ManyToOne
    private Account account;

    public Transaction() {
    }

    public Transaction(long amount, LocalDate timeStamp) {
        this.amount = amount;
        this.timeStamp = timeStamp;
    }

    public long getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public TransactionRule getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(TransactionRule createdBy) {
        this.createdBy = createdBy;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
