package br.com.esotk.my_first_rest_with_java_spring.service;

import br.com.esotk.my_first_rest_with_java_spring.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class UserServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(UserServices.class.getName());


    public User criarUsuario(User user) {
        logger.info("criando usuario");
        return user;
    }

    public User atualizarUsuario(User user) {
        logger.info("atualizando usuario");
        return user;
    }

    public void excluirUsuario(String id) {
        logger.info("deletando usuario");
    }

    public List<User> findAllIds() {
        logger.info("buscando todos os usuarios");
        var users = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = mockUser(i);
            users.add(user);
        }
        return users;
    }

    public User findById(String id) {
        logger.info("buscando usuario por ID");
        User user = new User();
        user.setId(counter.incrementAndGet());
        user.setName("Eduardo");
        user.setSobrenome("Silva");
        user.setEmail("eduardo@gmail.com");
        user.setGenero("Masculino");
        return user;
    }

    private User mockUser(int i) {
        User user = new User();
        user.setId(counter.incrementAndGet());
        user.setName("Nome " + i);
        user.setSobrenome("Sobrenome " + i);
        user.setEmail("user" + i + "@gmail.com");
        user.setGenero("Masculino");
        return user;
    }

}
