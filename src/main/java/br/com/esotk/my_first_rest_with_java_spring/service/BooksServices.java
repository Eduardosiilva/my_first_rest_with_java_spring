package br.com.esotk.my_first_rest_with_java_spring.service;

import br.com.esotk.my_first_rest_with_java_spring.controller.BooksController;
import br.com.esotk.my_first_rest_with_java_spring.data.dto.BooksDTO;
import br.com.esotk.my_first_rest_with_java_spring.exception.RequestWithObjectNullException;
import br.com.esotk.my_first_rest_with_java_spring.exception.ResourceNotFoundException;
import br.com.esotk.my_first_rest_with_java_spring.model.Books;
import br.com.esotk.my_first_rest_with_java_spring.repository.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.esotk.my_first_rest_with_java_spring.mapper.ObjectMapper.parseListObjects;
import static br.com.esotk.my_first_rest_with_java_spring.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BooksServices {

    private final Logger logger = LoggerFactory.getLogger(BooksServices.class.getName());

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    PagedResourcesAssembler<BooksDTO> assembler;

    public PagedModel<EntityModel<BooksDTO>> findAll(Pageable pageable) {
        logger.info("buscando todos os usuarios");

        var books = booksRepository.findAll(pageable);

        var booksWithLinks = books.map(book->{
            var dto = parseObject(book, BooksDTO.class);
            addHateoas(dto);
            return dto;
        });

        Link findAllLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BooksController.class)
                .findAll(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        String.valueOf(pageable.getSort())))
                .withSelfRel();


        return assembler.toModel(booksWithLinks, findAllLink);
    }

    public BooksDTO findById(Long id) {
        logger.info("buscando usuario por ID");
        var entity = booksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Books not found for this id :: " + id));

        var dto = parseObject(entity, BooksDTO.class);
        addHateoas(dto);
        return dto;
    }


    public BooksDTO criarBooks(BooksDTO booksDTO) {

        if (booksDTO == null) throw new RequestWithObjectNullException();

        logger.info("criando usuario");

        var entity = parseObject(booksDTO, Books.class);

        var dto =  parseObject(booksRepository.save(entity), BooksDTO.class);

        addHateoas(dto);

        return dto;
    }

    public BooksDTO atualizarBooks(BooksDTO booksDTO) {

        if (booksDTO == null) throw new RequestWithObjectNullException();

        logger.info("atualizando usuario");

        Books entity = booksRepository.findById(booksDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Books not found for this id :: " + booksDTO.getId()));

        entity.setAuthor(booksDTO.getAuthor());
        entity.setLaunchDate(booksDTO.getLaunchDate());
        entity.setPrice(booksDTO.getPrice());
        entity.setTitle(booksDTO.getTitle());;

        var dto = parseObject(booksRepository.save(entity), BooksDTO.class);
        addHateoas(dto);
        return dto;
    }
    public void excluirBooks(Long id) {
        logger.info("excluindo usuario");
        Books entity = booksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Books not found for this id :: " + id));
        booksRepository.delete(entity);
    }

    private void addHateoas(BooksDTO dto) {
        dto.add(linkTo(methodOn(BooksController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BooksController.class).findAll(1,2, "asc")).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BooksController.class).criarBooks(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BooksController.class).atualizarBooks(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BooksController.class).excluirBooks(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
