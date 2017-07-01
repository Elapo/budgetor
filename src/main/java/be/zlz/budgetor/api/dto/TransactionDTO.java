package be.zlz.budgetor.api.dto;

import be.zlz.budgetor.api.domain.Transaction;

import java.time.LocalDate;

public class TransactionDTO {

    private long id;

    private long amount;

    private LocalDate timeStamp;

    public TransactionDTO() {
    }

    public TransactionDTO(Transaction transaction) {
        id = transaction.getId();
        amount = transaction.getAmount();
        timeStamp = transaction.getTimeStamp();
    }

    public TransactionDTO(long amount, LocalDate timeStamp) {
        this.amount = amount;
        this.timeStamp = timeStamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
