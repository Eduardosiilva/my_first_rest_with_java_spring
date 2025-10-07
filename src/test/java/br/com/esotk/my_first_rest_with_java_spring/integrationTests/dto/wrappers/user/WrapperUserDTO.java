package br.com.esotk.my_first_rest_with_java_spring.integrationTests.dto.wrappers.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class WrapperUserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("_embedded")
    private UserEmbeddedDTO embedded;

}
