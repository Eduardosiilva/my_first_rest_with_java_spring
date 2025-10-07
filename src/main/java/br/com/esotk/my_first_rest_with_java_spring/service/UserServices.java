package br.com.esotk.my_first_rest_with_java_spring.service;

import br.com.esotk.my_first_rest_with_java_spring.controller.UserController;
import br.com.esotk.my_first_rest_with_java_spring.data.dto.UserDTO;
import br.com.esotk.my_first_rest_with_java_spring.exception.RequestWithObjectNullException;
import br.com.esotk.my_first_rest_with_java_spring.exception.ResourceNotFoundException;
import br.com.esotk.my_first_rest_with_java_spring.model.User;
import br.com.esotk.my_first_rest_with_java_spring.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import static br.com.esotk.my_first_rest_with_java_spring.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserServices {

    private final Logger logger = LoggerFactory.getLogger(UserServices.class.getName());

    @Autowired
    UserRepository userRepository;

    @Autowired
    PagedResourcesAssembler<UserDTO> assembler;

    public PagedModel<EntityModel<UserDTO>> findAll(Pageable pageable) {
        logger.info("buscando todos os usuarios");

        var users = userRepository.findAll(pageable);

        var usersWithLinks = users.map(user -> {
            var dto = parseObject(user, UserDTO.class);
            addHateoas(dto);
            return dto;
        });

        Link findAllLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .findAll(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        String.valueOf(pageable.getSort())))
                .withSelfRel();


        return assembler.toModel(usersWithLinks, findAllLink);
    }

    public UserDTO findById(Long id) {
        logger.info("buscando usuario por ID");
        var entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        var dto = parseObject(entity, UserDTO.class);
        addHateoas(dto);
        return dto;
    }


    public UserDTO criarUsuario(UserDTO userDTO) {

        if (userDTO == null) throw new RequestWithObjectNullException();

        logger.info("criando usuario");

        var entity = parseObject(userDTO, User.class);

        var dto =  parseObject(userRepository.save(entity), UserDTO.class);

        addHateoas(dto);

        return dto;
    }

    public UserDTO atualizarUsuario(UserDTO userDTO) {

        if (userDTO == null) throw new RequestWithObjectNullException();

        logger.info("atualizando usuario");

        User entity = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userDTO.getId()));

        entity.setNome(userDTO.getNome());
        entity.setSobrenome(userDTO.getSobrenome());
        entity.setEmail_pessoal(userDTO.getEmail_pessoal());
        entity.setGenero(userDTO.getGenero());

        var dto = parseObject(userRepository.save(entity), UserDTO.class);
        addHateoas(dto);
        return dto;
    }

    @Transactional
    public UserDTO disableUser(Long id) {
        logger.info("disable usuario");

        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        userRepository.disableUser(id);

        var entity = userRepository.findById(id).get();
        var dto = parseObject(entity, UserDTO.class);
        addHateoas(dto);
        return dto;
    }


    public void excluirUsuario(Long id) {
        logger.info("excluindo usuario");
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        userRepository.delete(entity);
    }

    private void addHateoas(UserDTO dto) {
        dto.add(linkTo(methodOn(UserController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(UserController.class).findAll(1, 12, "asc")).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(UserController.class).criarUsuario(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(UserController.class).atualizarUsuario(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(UserController.class).disableUser(dto.getId())).withRel("disable").withType("PATCH"));
        dto.add(linkTo(methodOn(UserController.class).excluirUsuario(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
