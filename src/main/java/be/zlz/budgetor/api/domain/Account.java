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

    @ManyToOne(cascade = ALL)
    private User user;

    public Account() {

    }

    public Account(long currentValue) {
        this.currentValue = currentValue;
        transactions = new ArrayList<Transaction>();
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

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", currentValue=" + currentValue +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
