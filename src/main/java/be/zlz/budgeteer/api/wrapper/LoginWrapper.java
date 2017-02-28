package be.zlz.budgeteer.api.wrapper;

import org.hibernate.validator.constraints.Email;

/**
 * Created by Frederik on 28/02/2017.
 */
public class LoginWrapper {

    @Email
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
