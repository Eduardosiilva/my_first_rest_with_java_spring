package br.com.esotk.my_first_rest_with_java_spring.controller;

import br.com.esotk.my_first_rest_with_java_spring.controller.docs.UserControllerDocs;
import br.com.esotk.my_first_rest_with_java_spring.data.dto.UserDTO;
import br.com.esotk.my_first_rest_with_java_spring.service.UserServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/v1")
@Tag(name = "User", description = "Endpoints for Managing Users")
public class UserController implements UserControllerDocs {

    @Autowired
    private UserServices services;

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE})

    @Override
    public ResponseEntity<PagedModel<EntityModel<UserDTO>>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ){
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC: Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "nome"));
        return ResponseEntity.ok(services.findAll(pageable));
    }

    @GetMapping(
            value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE})

    @Override
    public UserDTO findById(@PathVariable("id") Long id) {
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
    public UserDTO criarUsuario(@RequestBody UserDTO user) {

        return services.criarUsuario(user);
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
    public UserDTO atualizarUsuario(@RequestBody UserDTO user) {

        return services.atualizarUsuario(user);
    }

    @PatchMapping(value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE})

    @Override
    public UserDTO disableUser(@PathVariable("id") Long id) {
        return services.disableUser(id);
    }

    @DeleteMapping(value = "/{id}")

    @Override
    public ResponseEntity<?> excluirUsuario(@PathVariable("id") Long id) {
        services.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
