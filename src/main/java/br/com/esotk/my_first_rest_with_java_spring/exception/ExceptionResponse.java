package br.com.esotk.my_first_rest_with_java_spring.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {



}
