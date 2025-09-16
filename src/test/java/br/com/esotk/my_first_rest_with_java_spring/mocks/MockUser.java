package br.com.esotk.my_first_rest_with_java_spring.mocks;

import br.com.esotk.my_first_rest_with_java_spring.data.dto.UserDTO;
import br.com.esotk.my_first_rest_with_java_spring.model.User;

import java.util.ArrayList;
import java.util.List;

public class MockUser {

   public User mockEntity() {
       return mockEntity(0);
   }

   public UserDTO mockDTO() {
       return mockDTO(0);
   }

   public List<User> mockEntityList() {
       List<User> users = new ArrayList<>();
       for (int i = 0; i < 14; i++) {
           users.add(mockEntity((int) i));
       }
       return users;
   }

    public List<UserDTO> mockDTOList() {
         List<UserDTO> userDTOS = new ArrayList<>();
         for (int i = 0; i < 14; i++) {
              userDTOS.add(mockDTO(i));
         }
         return userDTOS;
   }


    public User mockEntity(Integer number) {
        User user = new User();
        user.setNome("Nome Teste" + number);
        user.setSobrenome("Sobrenome Teste" + number);
        user.setEmail_pessoal("teste" + number +"@gmail.com");
        user.setId(number.longValue());
        user.setGenero(number % 2 == 0 ? "Masculino" : "Feminino");
        return user;
    }
    
    public UserDTO mockDTO(Integer number) {
        UserDTO userDTO = new UserDTO();
        userDTO.setNome("Nome Teste" + number);
        userDTO.setSobrenome("Sobrenome Teste" + number);
        userDTO.setEmail_pessoal("teste" + number +"@gmail.com");
        userDTO.setId(number.longValue());
        userDTO.setGenero(number % 2 == 0 ? "Masculino" : "Feminino");
        return userDTO;
    }

}
