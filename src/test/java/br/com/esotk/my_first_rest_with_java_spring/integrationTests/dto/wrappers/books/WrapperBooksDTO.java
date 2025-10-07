package br.com.esotk.my_first_rest_with_java_spring.integrationTests.dto.wrappers.books;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class WrapperBooksDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("_embedded")
    private BooksEmbeddedDTO embedded;

}
