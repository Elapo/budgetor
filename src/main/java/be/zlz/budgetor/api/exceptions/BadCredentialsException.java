package be.zlz.budgetor.api.exceptions;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(){
        super();
    }

    public BadCredentialsException(String message){
        super(message);
    }
}
