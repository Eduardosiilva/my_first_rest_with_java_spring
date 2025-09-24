package br.com.esotk.my_first_rest_with_java_spring.service;

import br.com.esotk.my_first_rest_with_java_spring.data.dto.UserDTO;
import br.com.esotk.my_first_rest_with_java_spring.exception.RequestWithObjectNullException;
import br.com.esotk.my_first_rest_with_java_spring.mocks.MockUser;
import br.com.esotk.my_first_rest_with_java_spring.model.User;
import br.com.esotk.my_first_rest_with_java_spring.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserServicesTest {

    MockUser input;

    @InjectMocks
    private UserServices service;

    @Mock
    UserRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockUser();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<User> listaDeUsuarios = input.mockEntityList();

        when(repository.findAll()).thenReturn(listaDeUsuarios);

        var users = service.findAll();
        assertNotNull(users);
        assertEquals(14, users.size());

        var usuario = users.get(1);
        assertNotNull(usuario);
        assertNotNull(usuario.getId());
        assertNotNull(usuario.getNome());
        assertNotNull(usuario.getSobrenome());
        assertNotNull(usuario.getEmail_pessoal());
        assertNotNull(usuario.getGenero());
        assertNotNull(usuario.getLinks());
        usuario.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/user/v1/2")
                        && link.getType().equals("GET"));
        usuario.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/user/v1")
                        && link.getType().equals("GET"));
        usuario.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/user/v1")
                        && link.getType().equals("POST"));
        usuario.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/user/v1")
                        && link.getType().equals("PUT"));
        usuario.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/user/v1/2")
                        && link.getType().equals("DELETE"));

        assertEquals("Nome Teste1", usuario.getNome());
        assertEquals("Sobrenome Teste1", usuario.getSobrenome());
        assertEquals("teste1@gmail.com", usuario.getEmail_pessoal());
        assertEquals("Feminino", usuario.getGenero());
    }

    @Test
    void findById() {
        User usuario = input.mockEntity(1);
        usuario.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(usuario));
        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getNome());
        assertNotNull(result.getSobrenome());
        assertNotNull(result.getEmail_pessoal());
        assertNotNull(result.getGenero());
        assertNotNull(result.getLinks());
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/user/v1/1")
                        && link.getType().equals("GET"));
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/user/v1")
                        && link.getType().equals("GET"));
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/user/v1")
                        && link.getType().equals("POST"));
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/user/v1")
                        && link.getType().equals("PUT"));
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/user/v1/1")
                        && link.getType().equals("DELETE"));

        assertEquals("Nome Teste1", result.getNome());
        assertEquals("Sobrenome Teste1", result.getSobrenome());
        assertEquals("teste1@gmail.com", result.getEmail_pessoal());
        assertEquals("Feminino", result.getGenero());

    }

    @Test
    void criarUsuario() {
        User usuarioSalvo = input.mockEntity(1);
        usuarioSalvo.setId(1L);
        UserDTO usuarioDTO = input.mockDTO(1);

        when(repository.save(usuarioSalvo)).thenReturn(usuarioSalvo);
        {
        var result = service.criarUsuario(usuarioDTO);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getNome());
        assertNotNull(result.getSobrenome());
        assertNotNull(result.getEmail_pessoal());
        assertNotNull(result.getGenero());
        assertNotNull(result.getLinks());
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/user/v1/1")
                        && link.getType().equals("GET"));
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/user/v1")
                        && link.getType().equals("GET"));
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/user/v1")
                        && link.getType().equals("POST"));
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/user/v1")
                        && link.getType().equals("PUT"));
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/user/v1/1")
                        && link.getType().equals("DELETE"));

        assertEquals("Nome Teste1", result.getNome());
        assertEquals("Sobrenome Teste1", result.getSobrenome());
        assertEquals("teste1@gmail.com", result.getEmail_pessoal());
        assertEquals("Feminino", result.getGenero());

        }
    }

    @Test
    void criarUsuarioComValorNulo() {

        Exception exception = assertThrows(RequestWithObjectNullException.class, () -> {
            service.criarUsuario(null);
        });

        String expectedMessage = "Não é possivel fazer uma requisição com objeto nulo.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void atualizarUsuario() {
        User usuarioSalvo = input.mockEntity(1);
        usuarioSalvo.setId(1L);
        UserDTO usuarioDTO = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(usuarioSalvo));
        when(repository.save(usuarioSalvo)).thenReturn(usuarioSalvo);
        {
            var result = service.atualizarUsuario(usuarioDTO);
            assertNotNull(result);
            assertNotNull(result.getId());
            assertNotNull(result.getNome());
            assertNotNull(result.getSobrenome());
            assertNotNull(result.getEmail_pessoal());
            assertNotNull(result.getGenero());
            assertNotNull(result.getLinks());
            result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("self")
                            && link.getHref().endsWith("/api/user/v1/1")
                            && link.getType().equals("GET"));
            result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("findAll")
                            && link.getHref().endsWith("/api/user/v1")
                            && link.getType().equals("GET"));
            result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("create")
                            && link.getHref().endsWith("/api/user/v1")
                            && link.getType().equals("POST"));
            result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("update")
                            && link.getHref().endsWith("/api/user/v1")
                            && link.getType().equals("PUT"));
            result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("delete")
                            && link.getHref().endsWith("/api/user/v1/1")
                            && link.getType().equals("DELETE"));

            assertEquals("Nome Teste1", result.getNome());
            assertEquals("Sobrenome Teste1", result.getSobrenome());
            assertEquals("teste1@gmail.com", result.getEmail_pessoal());
            assertEquals("Feminino", result.getGenero());

        }
    }

    @Test
    void atualizarUsuarioComValorNulo() {
        Exception exception = assertThrows(RequestWithObjectNullException.class, () -> {
            service.atualizarUsuario(null);
        });

        String expectedMessage = "Não é possivel fazer uma requisição com objeto nulo.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void excluirUsuario() {
        User usuario = input.mockEntity(1);
        usuario.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(usuario));
        service.excluirUsuario(1L);

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(User.class));
        verifyNoMoreInteractions(repository);
    }
}