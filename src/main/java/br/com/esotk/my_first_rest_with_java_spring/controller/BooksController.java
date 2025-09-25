package br.com.esotk.my_first_rest_with_java_spring.controller;

import br.com.esotk.my_first_rest_with_java_spring.controller.docs.BooksControllerDocs;
import br.com.esotk.my_first_rest_with_java_spring.data.dto.BooksDTO;
import br.com.esotk.my_first_rest_with_java_spring.service.BooksServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books/v1")
@Tag(name = "Books", description = "Endpoints for Managing Books")
public class BooksController implements BooksControllerDocs {

    @Autowired
    private BooksServices services;

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE})

    @Override
    public List<BooksDTO> findAll() {

        return services.findAll();
    }

    @GetMapping(
            value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE})

    @Override
    public BooksDTO findById(@PathVariable("id") Long id) {

        return services.findById(id);
    }

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE})

    @Override
    public BooksDTO criarBooks(@RequestBody BooksDTO books) {

        return services.criarBooks(books);
    }

    @PutMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE})

    @Override
    public BooksDTO atualizarBooks(@RequestBody BooksDTO books) {

        return services.atualizarBooks(books);
    }

    @DeleteMapping(value = "/{id}")

    @Override
    public ResponseEntity<?> excluirBooks(@PathVariable("id") Long id) {
        services.excluirBooks(id);
        return ResponseEntity.noContent().build();
    }

}
