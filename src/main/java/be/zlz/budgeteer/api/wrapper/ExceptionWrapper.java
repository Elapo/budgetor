package be.zlz.budgeteer.api.wrapper;

/**
 * Created by Frederik on 28/02/2017.
 */
public class ExceptionWrapper {
    private String message;
    private int code;

    public ExceptionWrapper(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}