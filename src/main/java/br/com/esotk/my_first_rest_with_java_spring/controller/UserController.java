package br.com.esotk.my_first_rest_with_java_spring.controller;

import br.com.esotk.my_first_rest_with_java_spring.model.User;
import br.com.esotk.my_first_rest_with_java_spring.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices services;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<User> findAll() {
        return services.findAllIds();
    }

    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User findById(@PathVariable("id") String id) {
       return services.findById(id);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User criarUsuario(@RequestBody User user) {
        return services.criarUsuario(user);
    }


    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User atualizarUsuario(@RequestBody User user) {
        return services.atualizarUsuario(user);
    }

    @RequestMapping( value = "/{id}",
            method = RequestMethod.DELETE
    )
    public void excluirUsuario(@PathVariable("id") String id) {
        services.excluirUsuario(id);
    }

}
