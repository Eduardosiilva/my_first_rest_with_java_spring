package br.com.esotk.my_first_rest_with_java_spring.controller;

import br.com.esotk.my_first_rest_with_java_spring.config.TestConfigs;
import br.com.esotk.my_first_rest_with_java_spring.integrationTests.dto.BooksDTO;
import br.com.esotk.my_first_rest_with_java_spring.integrationTests.dto.wrappers.books.WrapperBooksDTO;
import br.com.esotk.my_first_rest_with_java_spring.integrationTests.testsIntegration.AbstractIntegrationTests;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BooksControllerTest extends AbstractIntegrationTests {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static BooksDTO books;

    @LocalServerPort
    private int port;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        books = new BooksDTO();
    }

    @Test
    @Order(1)
    void criarBooks() throws JsonProcessingException {
        mockBooks();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST)
                .setBasePath("/api/books/v1")
                .setPort(port)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(books)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        BooksDTO createdBooks = objectMapper.readValue(content, BooksDTO.class);
        books = createdBooks;

        assertNotNull(createdBooks.getId());
        assertTrue(createdBooks.getId() > 0);

        assertEquals("Michael C. Feathers", createdBooks.getAuthor());
        assertEquals("2017-11-29 13:50:05.878000", createdBooks.getLaunchDate());
        assertEquals(49.0, createdBooks.getPrice());
        assertEquals("Working effectively with legacy code", createdBooks.getTitle());
    }

    @Test
    @Order(2)
    void atualizarBooks() throws JsonProcessingException {
        books.setPrice(48.0);

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(books)
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        BooksDTO createdBooks = objectMapper.readValue(content, BooksDTO.class);
        books = createdBooks;

        assertNotNull(createdBooks.getId());
        assertTrue(createdBooks.getId() > 0);

        assertEquals("Michael C. Feathers", createdBooks.getAuthor());
        assertEquals("2017-11-29 13:50:05.878000", createdBooks.getLaunchDate());
        assertEquals(48.0, createdBooks.getPrice());
        assertEquals("Working effectively with legacy code", createdBooks.getTitle());
    }

    @Test
    @Order(3)
    void findById() throws JsonProcessingException {

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", books.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        BooksDTO createdBooks = objectMapper.readValue(content, BooksDTO.class);
        books = createdBooks;

        assertNotNull(createdBooks.getId());
        assertTrue(createdBooks.getId() > 0);

        assertEquals("Michael C. Feathers", createdBooks.getAuthor());
        assertEquals("2017-11-29 13:50:05.878000", createdBooks.getLaunchDate());
        assertEquals(48.0, createdBooks.getPrice());
        assertEquals("Working effectively with legacy code", createdBooks.getTitle());
    }

    @Test
    @Order(4)
    void excluirBooks() {
        given(specification)
                .pathParam("id", books.getId())
                .when()
                .delete("{id}")
                .then()
                .statusCode(204);
    }

    @Test
    @Order(5)
    void findAll() throws JsonProcessingException {

        var content = given(specification)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .queryParam("page", 3)
            .queryParam("size", 2)
            .queryParam("direction", "asc")
            .when()
            .get()
            .then()
            .statusCode(200)
            .extract()
            .body()
            .asString();

        WrapperBooksDTO wrapper = objectMapper.readValue(content, WrapperBooksDTO.class);
        List<BooksDTO> books = wrapper.getEmbedded().getBooks();

        BooksDTO booksOne = books.get(0);


        assertNotNull(booksOne.getId());
        assertTrue(booksOne.getId() > 0);

        assertEquals("Eric Freeman, Elisabeth Freeman, Kathy Sierra, Bert Bates", booksOne.getAuthor());
        assertEquals("2017-11-07 15:09:01.674000", booksOne.getLaunchDate());
        assertEquals(110.0, booksOne.getPrice());
        assertEquals("Head First Design Patterns", booksOne.getTitle());
    }

    private void mockBooks() {
        books.setAuthor("Michael C. Feathers");
        books.setLaunchDate("2017-11-29 13:50:05.878000");
        books.setPrice(49.0);
        books.setTitle("Working effectively with legacy code");
    }
}