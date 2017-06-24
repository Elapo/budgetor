package be.zlz.budgetor.api.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @Column(unique = true)
    @NotBlank
    private String emailAddress;

    @NotBlank
    private String passwordHash;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Account> accounts;

    @OneToMany(fetch = FetchType.LAZY)
    private List<SavingsAccount> savingsAccounts;

    public User() {
        this.accounts = new ArrayList<>();
        this.savingsAccounts = new ArrayList<>();
    }

    public User(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.accounts = new ArrayList<>();
        this.savingsAccounts = new ArrayList<>();
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
