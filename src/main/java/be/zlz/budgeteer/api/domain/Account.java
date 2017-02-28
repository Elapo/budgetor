package be.zlz.budgeteer.api.domain;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long currentValue;

    public Account() {
    }

    public Account(long currentValue) {
        this.currentValue = currentValue;
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
}
