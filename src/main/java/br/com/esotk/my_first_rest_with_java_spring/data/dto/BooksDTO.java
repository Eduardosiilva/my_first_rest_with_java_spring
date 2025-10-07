package br.com.esotk.my_first_rest_with_java_spring.data.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Relation(collectionRelation = "books")
public class BooksDTO extends RepresentationModel<BooksDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String author;
    private String launchDate;
    private Double price;
    private String title;

}
