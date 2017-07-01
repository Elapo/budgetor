package be.zlz.budgetor.api.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long currentValue;

    @Length(max = 254)
    private String nickName;

    @OneToMany
    private List<Transaction> transactions;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany
    private List<TransactionRule> rules;

    public Account() {

    }

    public Account(long currentValue, String nickName, User user) {
        this.currentValue = currentValue;
        this.nickName = nickName;
        this.user = user;
    }

    public Account(long currentValue) {
        this.currentValue = currentValue;
        transactions = new ArrayList<>();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public long getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<TransactionRule> getRules() {
        return rules;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", currentValue=" + currentValue +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
