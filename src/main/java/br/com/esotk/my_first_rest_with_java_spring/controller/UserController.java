package br.com.esotk.my_first_rest_with_java_spring.controller;

import br.com.esotk.my_first_rest_with_java_spring.controller.docs.UserControllerDocs;
import br.com.esotk.my_first_rest_with_java_spring.data.dto.UserDTO;
import br.com.esotk.my_first_rest_with_java_spring.model.User;
import br.com.esotk.my_first_rest_with_java_spring.service.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<UserDTO> findAll() {

        return services.findAll();
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
