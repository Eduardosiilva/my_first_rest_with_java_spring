package br.com.esotk.my_first_rest_with_java_spring.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonPropertyOrder({"id", "nome", "sobrenome", "Genero", "email_pessoal"})
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String sobrenome;
    @JsonProperty("email")
    private String email_pessoal;
    @JsonIgnore
    private String Genero;
}
