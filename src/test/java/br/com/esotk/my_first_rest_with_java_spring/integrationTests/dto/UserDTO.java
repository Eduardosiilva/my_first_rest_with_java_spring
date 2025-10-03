package br.com.esotk.my_first_rest_with_java_spring.integrationTests.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


@Data
public class UserDTO  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String sobrenome;
    private String Genero;
    private String email;
    private Boolean enabled;

}
