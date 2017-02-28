package be.zlz.budgeteer.api.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    @Email
    private String emailAddress;

    private String passwordHash;

    @OneToMany
    private List<Account> accounts;

    @OneToMany
    private List<SavingsAccount> savingsAccounts;

    public User() {
        this.accounts = new ArrayList<Account>();
        this.savingsAccounts = new ArrayList<SavingsAccount>();
    }

    public User(String firstName, String lastName, String emailAddress, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.passwordHash = passwordHash;
        this.accounts = new ArrayList<Account>();
        this.savingsAccounts = new ArrayList<SavingsAccount>();
    }

    public long getId() {
        return id;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<SavingsAccount> getSavingsAccounts() {
        return savingsAccounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
