package br.com.esotk.my_first_rest_with_java_spring.repository;

import br.com.esotk.my_first_rest_with_java_spring.integrationTests.testsIntegration.AbstractIntegrationTests;
import br.com.esotk.my_first_rest_with_java_spring.model.User;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest extends AbstractIntegrationTests {
    @Autowired
    UserRepository repository;

    private static User user;

    @BeforeAll
    static void setUp() {
        user = new User();
    }

    @Test
    @Order(1)
    void findUsersByName() {
        Pageable pageable = PageRequest.of(
                0,
                12,
                Sort.by(Sort.Direction.ASC, "nome"));

        user = repository.findUsersByName("Edu", pageable).getContent().get(0);

        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals("Eduardo", user.getNome());
        assertEquals("Silva", user.getSobrenome());
        assertEquals("Masculino", user.getGenero());
        assertEquals("EduardoS@gmail.com", user.getEmail_pessoal());
        assertTrue(user.getEnabled());

    }

    @Test
    @Order(2)
    void disableUser() {

        Long id = user.getId();
        repository.disableUser(id);

        var result = repository.findById(id);
        user = result.get();

        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals("Eduardo", user.getNome());
        assertEquals("Silva", user.getSobrenome());
        assertEquals("Masculino", user.getGenero());
        assertEquals("EduardoS@gmail.com", user.getEmail_pessoal());
        assertFalse(user.getEnabled());


    }
}