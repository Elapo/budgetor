package be.zlz.budgetor.api.dto;

import org.hibernate.validator.constraints.Email;

/**
 * Created by Frederik on 28/02/2017.
 */
public class LoginDTO {

    @Email
    private String email;

    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

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
