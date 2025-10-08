package br.com.esotk.my_first_rest_with_java_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileStorageNotFoundException extends RuntimeException {

    public FileStorageNotFoundException(String message) {
        super(message);
    }

    public FileStorageNotFoundException(String message, Throwable cause) {

        super(message,cause);
    }

}
