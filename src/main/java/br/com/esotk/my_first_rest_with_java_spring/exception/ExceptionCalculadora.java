package br.com.esotk.my_first_rest_with_java_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionCalculadora extends RuntimeException {

    public ExceptionCalculadora(String message) {
        super(message);
    }
}
