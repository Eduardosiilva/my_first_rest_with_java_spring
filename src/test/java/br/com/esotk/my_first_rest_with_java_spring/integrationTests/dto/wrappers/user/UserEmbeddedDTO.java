package br.com.esotk.my_first_rest_with_java_spring.integrationTests.dto.wrappers.user;

import br.com.esotk.my_first_rest_with_java_spring.integrationTests.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class UserEmbeddedDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("User")
    private List<UserDTO> user;


}
