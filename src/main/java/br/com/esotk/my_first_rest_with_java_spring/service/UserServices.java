package br.com.esotk.my_first_rest_with_java_spring.service;

import br.com.esotk.my_first_rest_with_java_spring.exception.ResourceNotFoundException;
import br.com.esotk.my_first_rest_with_java_spring.model.User;
import br.com.esotk.my_first_rest_with_java_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class UserServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        logger.info("buscando todos os usuarios");
        return userRepository.findAll();
    }

    public User findById(Long id) {
        logger.info("buscando usuario por ID");
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
    }


    public User criarUsuario(User user) {
        logger.info("criando usuario");
        return userRepository.save(user);
    }

    public User atualizarUsuario(User user) {
        logger.info("atualizando usuario");
        User entity = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + user.getId()));

        entity.setNome(user.getNome());
        entity.setSobrenome(user.getSobrenome());
        entity.setEmail_pessoal(user.getEmail_pessoal());
        entity.setGenero(user.getGenero());
        return userRepository.save(entity);
    }
    public void excluirUsuario(Long id) {
        logger.info("excluindo usuario");
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        userRepository.delete(entity);
    }

}
