package br.com.esotk.my_first_rest_with_java_spring.integrationTests.dto.wrappers.books;

import br.com.esotk.my_first_rest_with_java_spring.integrationTests.dto.BooksDTO;
import br.com.esotk.my_first_rest_with_java_spring.integrationTests.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class BooksEmbeddedDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("books")
    private List<BooksDTO> books;


}
