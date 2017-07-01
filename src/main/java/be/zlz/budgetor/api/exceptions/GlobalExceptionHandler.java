package be.zlz.budgetor.api.exceptions;

import be.zlz.budgetor.api.dto.ExceptionWrapper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest webRequest){
        return new ResponseEntity<>(new ExceptionWrapper(ex.getMessage(), HttpStatus.FORBIDDEN.value()), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(Exception ex, WebRequest webRequest){
        return new ResponseEntity<>(new ExceptionWrapper(ex.getMessage(), HttpStatus.UNAUTHORIZED.value()), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(Exception ex, WebRequest webRequest){
        return new ResponseEntity<>(new ExceptionWrapper("Resource already exists.", HttpStatus.BAD_REQUEST.value()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
