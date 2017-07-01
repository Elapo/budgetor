package be.zlz.budgetor.api.exceptions;

import be.zlz.budgetor.api.dto.ExceptionWrapper;
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
        return new ResponseEntity<Object>(new ExceptionWrapper(ex.getMessage(), HttpStatus.FORBIDDEN.value()), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}
