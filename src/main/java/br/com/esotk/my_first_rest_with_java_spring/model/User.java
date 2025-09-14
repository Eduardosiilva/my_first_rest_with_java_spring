package br.com.esotk.my_first_rest_with_java_spring.model;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String sobrenome;
    private String email;
    private String Genero;
}

