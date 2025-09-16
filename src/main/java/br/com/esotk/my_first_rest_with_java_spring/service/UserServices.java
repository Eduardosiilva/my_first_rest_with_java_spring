package br.com.esotk.my_first_rest_with_java_spring.service;

import br.com.esotk.my_first_rest_with_java_spring.data.dto.UserDTO;
import br.com.esotk.my_first_rest_with_java_spring.exception.ResourceNotFoundException;
import br.com.esotk.my_first_rest_with_java_spring.model.User;
import br.com.esotk.my_first_rest_with_java_spring.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static br.com.esotk.my_first_rest_with_java_spring.mapper.ObjectMapper.parseListObjects;
import static br.com.esotk.my_first_rest_with_java_spring.mapper.ObjectMapper.parseObject;

@Service
public class UserServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(UserServices.class.getName());

    @Autowired
    UserRepository userRepository;

    public List<UserDTO> findAll() {
        logger.info("buscando todos os usuarios");
        List<UserDTO> users = parseListObjects(userRepository.findAll(), UserDTO.class);
        return users;
    }

    public UserDTO findById(Long id) {
        logger.info("buscando usuario por ID");
        var entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        return parseObject(entity, UserDTO.class);
    }


    public UserDTO criarUsuario(UserDTO userDTO) {
        logger.info("criando usuario");

        var entity = parseObject(userDTO, User.class);

        return parseObject(userRepository.save(entity), UserDTO.class);
    }

    public UserDTO atualizarUsuario(UserDTO userDTO) {
        logger.info("atualizando usuario");
        User entity = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userDTO.getId()));

        entity.setNome(userDTO.getNome());
        entity.setSobrenome(userDTO.getSobrenome());
        entity.setEmail_pessoal(userDTO.getEmail_pessoal());
        entity.setGenero(userDTO.getGenero());
        return parseObject(userRepository.save(entity), UserDTO.class);
    }
    public void excluirUsuario(Long id) {
        logger.info("excluindo usuario");
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        userRepository.delete(entity);
    }

}
