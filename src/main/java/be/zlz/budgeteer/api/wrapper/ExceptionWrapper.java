package be.zlz.budgeteer.api.wrapper;

/**
 * Created by Frederik on 28/02/2017.
 */
public class ExceptionWrapper {
    private String error;
    private int code;

    public ExceptionWrapper(String message, int code) {
        this.error = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
