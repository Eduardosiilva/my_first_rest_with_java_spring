package br.com.esotk.my_first_rest_with_java_spring.exception;


public class ExceptionCalculadora extends RuntimeException {
    public ExceptionCalculadora(String message) {
        super(message);
    }
}
