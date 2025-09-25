package br.com.esotk.my_first_rest_with_java_spring.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonPropertyOrder({"id", "nome", "sobrenome", "Genero", "email_pessoal"})
public class UserDTO extends RepresentationModel<UserDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String sobrenome;
    @JsonProperty("email")
    private String email_pessoal;
    private String Genero;
}
