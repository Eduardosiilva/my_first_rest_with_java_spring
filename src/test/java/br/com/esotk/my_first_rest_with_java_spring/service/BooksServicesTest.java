package br.com.esotk.my_first_rest_with_java_spring.service;

import br.com.esotk.my_first_rest_with_java_spring.data.dto.BooksDTO;
import br.com.esotk.my_first_rest_with_java_spring.data.dto.UserDTO;
import br.com.esotk.my_first_rest_with_java_spring.exception.RequestWithObjectNullException;
import br.com.esotk.my_first_rest_with_java_spring.mocks.MockBooks;
import br.com.esotk.my_first_rest_with_java_spring.mocks.MockUser;
import br.com.esotk.my_first_rest_with_java_spring.model.Books;
import br.com.esotk.my_first_rest_with_java_spring.model.User;
import br.com.esotk.my_first_rest_with_java_spring.repository.BooksRepository;
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
class BooksServicesTest {

    MockBooks input;

    @InjectMocks
    private BooksServices service;

    @Mock
    BooksRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBooks();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Books> listaDeBooks = input.mockEntityList();

        when(repository.findAll()).thenReturn(listaDeBooks);

        var books = service.findAll();
        assertNotNull(books);
        assertEquals(14, books.size());

        var usuario = books.get(1);
        assertNotNull(usuario);
        assertNotNull(usuario.getId());
        assertNotNull(usuario.getAuthor());
        assertNotNull(usuario.getLaunchDate());
        assertNotNull(usuario.getPrice());
        assertNotNull(usuario.getTitle());
        assertNotNull(usuario.getLinks());
        usuario.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/books/v1/2")
                        && link.getType().equals("GET"));
        usuario.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("GET"));
        usuario.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("POST"));
        usuario.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("PUT"));
        usuario.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/books/v1/2")
                        && link.getType().equals("DELETE"));

        assertEquals("Author Teste1", usuario.getAuthor());
        assertEquals("2020-01-01", usuario.getLaunchDate());
        assertEquals(26.0, usuario.getPrice());
        assertEquals("Title Teste1", usuario.getTitle());
    }

    @Test
    void findById() {
        Books books = input.mockEntity(1);
        books.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(books));
        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertNotNull(result.getPrice());
        assertNotNull(result.getTitle());
        assertNotNull(result.getLinks());
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/books/v1/1")
                        && link.getType().equals("GET"));
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("GET"));
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("POST"));
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/books/v1")
                        && link.getType().equals("PUT"));
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/books/v1/1")
                        && link.getType().equals("DELETE"));

        assertEquals("Author Teste1", result.getAuthor());
        assertEquals("2020-01-01", result.getLaunchDate());
        assertEquals(26.0, result.getPrice());
        assertEquals("Title Teste1", result.getTitle());

    }

    @Test
    void criarUsuario() {
        Books bookSalvo = input.mockEntity(1);
        bookSalvo.setId(1L);
        BooksDTO booksDTO = input.mockDTO(1);

        when(repository.save(bookSalvo)).thenReturn(bookSalvo);
        {
        var result = service.criarBooks(booksDTO);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertNotNull(result.getPrice());
        assertNotNull(result.getTitle());
        assertNotNull(result.getLinks());
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("GET"));
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET"));
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("POST"));
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT"));
        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("DELETE"));

        assertEquals("Author Teste1", result.getAuthor());
        assertEquals("2020-01-01", result.getLaunchDate());
        assertEquals(26.0, result.getPrice());
        assertEquals("Title Teste1", result.getTitle());
        }
    }

    @Test
    void criarUsuarioComValorNulo() {

        Exception exception = assertThrows(RequestWithObjectNullException.class, () -> {
            service.criarBooks(null);
        });

        String expectedMessage = "Não é possivel fazer uma requisição com objeto nulo.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void atualizarUsuario() {
        Books bookSalvo = input.mockEntity(1);
        bookSalvo.setId(1L);
        BooksDTO booksDTO = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(bookSalvo));
        when(repository.save(bookSalvo)).thenReturn(bookSalvo);
        {
            var result = service.atualizarBooks(booksDTO);
            assertNotNull(result);
            assertNotNull(result.getId());
            assertNotNull(result.getAuthor());
            assertNotNull(result.getLaunchDate());
            assertNotNull(result.getPrice());
            assertNotNull(result.getTitle());
            assertNotNull(result.getLinks());
            result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("self")
                            && link.getHref().endsWith("/api/books/v1/1")
                            && link.getType().equals("GET"));
            result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("findAll")
                            && link.getHref().endsWith("/api/books/v1")
                            && link.getType().equals("GET"));
            result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("create")
                            && link.getHref().endsWith("/api/books/v1")
                            && link.getType().equals("POST"));
            result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("update")
                            && link.getHref().endsWith("/api/books/v1")
                            && link.getType().equals("PUT"));
            result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("delete")
                            && link.getHref().endsWith("/api/user/v1/1")
                            && link.getType().equals("DELETE"));

            assertEquals("Author Teste1", result.getAuthor());
            assertEquals("2020-01-01", result.getLaunchDate());
            assertEquals(26.0, result.getPrice());
            assertEquals("Title Teste1", result.getTitle());

        }
    }

    @Test
    void atualizarUsuarioComValorNulo() {
        Exception exception = assertThrows(RequestWithObjectNullException.class, () -> {
            service.atualizarBooks(null);
        });

        String expectedMessage = "Não é possivel fazer uma requisição com objeto nulo.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void excluirUsuario() {
        Books books = input.mockEntity(1);
        books.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(books));
        service.excluirBooks(1L);

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Books.class));
        verifyNoMoreInteractions(repository);
    }
}