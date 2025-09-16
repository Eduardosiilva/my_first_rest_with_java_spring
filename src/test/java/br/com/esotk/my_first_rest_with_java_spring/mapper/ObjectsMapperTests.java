package br.com.esotk.my_first_rest_with_java_spring.mapper;

import br.com.esotk.my_first_rest_with_java_spring.data.dto.UserDTO;
import br.com.esotk.my_first_rest_with_java_spring.mocks.MockUser;
import br.com.esotk.my_first_rest_with_java_spring.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static br.com.esotk.my_first_rest_with_java_spring.mapper.ObjectMapper.parseListObjects;
import static br.com.esotk.my_first_rest_with_java_spring.mapper.ObjectMapper.parseObject;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectsMapperTests {

    MockUser inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockUser();
    }

    @Test
    public void parseEntityToDTOTest() {
        UserDTO output = parseObject(inputObject.mockEntity(), UserDTO.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Nome Teste0", output.getNome());
        assertEquals("Sobrenome Teste0", output.getSobrenome());
        assertEquals("teste0@gmail.com", output.getEmail_pessoal());
        assertEquals("Masculino", output.getGenero());
    }

    @Test
    public void parseDTOToEntityTest() {
        User output = parseObject(inputObject.mockDTO(), User.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Nome Teste0", output.getNome());
        assertEquals("Sobrenome Teste0", output.getSobrenome());
        assertEquals("teste0@gmail.com", output.getEmail_pessoal());
        assertEquals("Masculino", output.getGenero());
    }

    @Test
    public void parseEntityListToDTOListTest() {
        List<User> outputList = parseListObjects(inputObject.mockEntityList(), User.class);
        User userOne = outputList.get(1);
        assertEquals(Long.valueOf(1L), userOne.getId());
        assertEquals("Nome Teste1", userOne.getNome());
        assertEquals("Sobrenome Teste1", userOne.getSobrenome());
        assertEquals("teste1@gmail.com", userOne.getEmail_pessoal());
        assertEquals("Feminino", userOne.getGenero());

        User userFour = outputList.get(4);
        assertEquals(Long.valueOf(4L), userFour.getId());
        assertEquals("Nome Teste4", userFour.getNome());
        assertEquals("Sobrenome Teste4", userFour.getSobrenome());
        assertEquals("teste4@gmail.com", userFour.getEmail_pessoal());
        assertEquals("Masculino", userFour.getGenero());

        User userSeven = outputList.get(7);
        assertEquals(Long.valueOf(7L), userSeven.getId());
        assertEquals("Nome Teste7", userSeven.getNome());
        assertEquals("Sobrenome Teste7", userSeven.getSobrenome());
        assertEquals("teste7@gmail.com", userSeven.getEmail_pessoal());
        assertEquals("Feminino", userSeven.getGenero());
    }

    @Test
    public void parseDTOListToEntityListTest() {
        List<UserDTO> outputList = parseListObjects(inputObject.mockDTOList(), UserDTO.class);
        UserDTO userOne = outputList.get(1);
        assertEquals(Long.valueOf(1L), userOne.getId());
        assertEquals("Nome Teste1", userOne.getNome());
        assertEquals("Sobrenome Teste1", userOne.getSobrenome());
        assertEquals("teste1@gmail.com", userOne.getEmail_pessoal());
        assertEquals("Feminino", userOne.getGenero());

        UserDTO userFour = outputList.get(4);
        assert userFour.getId().equals(4L);
        assert userFour.getNome().equals("Nome Teste4");
        assert userFour.getSobrenome().equals("Sobrenome Teste4");
        assert userFour.getEmail_pessoal().equals("teste4@gmail.com");
        assert userFour.getGenero().equals("Masculino");

        UserDTO userSeven = outputList.get(7);
        assertEquals(Long.valueOf(7L), userSeven.getId());
        assertEquals("Nome Teste7", userSeven.getNome());
        assertEquals("Sobrenome Teste7", userSeven.getSobrenome());
        assertEquals("teste7@gmail.com", userSeven.getEmail_pessoal());
        assertEquals("Feminino", userSeven.getGenero());
    }
}
