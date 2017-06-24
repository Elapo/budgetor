package be.zlz.budgetor.api.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SavingsAccount {

    @Id
    @GeneratedValue
    private long id;

    private String nickName;

    private long currentValue;

    @OneToMany
    private List<Transaction> transactions;

    @OneToMany
    private List<TransactionRule> autoRules;

    public SavingsAccount() {
        this.transactions = new ArrayList<Transaction>();
        this.autoRules = new ArrayList<TransactionRule>();
    }

    public SavingsAccount(long currentValue) {
        this.currentValue = currentValue;
        this.transactions = new ArrayList<Transaction>();
        this.autoRules = new ArrayList<TransactionRule>();
    }

    public long getId() {
        return id;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<TransactionRule> getAutoRules() {
        return autoRules;
    }

    public long getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(long currentValue) {
        this.currentValue = currentValue;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", currentValue=" + currentValue +
                '}';
    }
}
