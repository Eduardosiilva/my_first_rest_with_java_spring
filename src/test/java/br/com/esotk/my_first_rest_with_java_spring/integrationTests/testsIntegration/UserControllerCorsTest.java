package br.com.esotk.my_first_rest_with_java_spring.integrationTests.testsIntegration;


import br.com.esotk.my_first_rest_with_java_spring.config.TestConfigs;
import br.com.esotk.my_first_rest_with_java_spring.integrationTests.dto.UserDTO;
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

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerCorsTest extends AbstractIntegrationTests {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static UserDTO user;

    @LocalServerPort
    private int port;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        user = new UserDTO();
    }

    @Test
    @Order(1)
    void criarUsuarioComOringiCorreta() throws JsonProcessingException {
        mockUser();

        specification = new RequestSpecBuilder()
            .addHeader(TestConfigs.HEADER_PARM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST)
            .setBasePath("/api/user/v1")
            .setPort(port)
            .addFilter(new RequestLoggingFilter(LogDetail.ALL))
            .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
            .build();

        var content = given(specification)
             .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(user)
             .when()
                .post()
             .then()
                .statusCode(200)
             .extract()
                .body()
                   .asString();

        UserDTO createdUser = objectMapper.readValue(content, UserDTO.class);
        user = createdUser;

       assertNotNull(createdUser.getId());
       assertNotNull(createdUser.getNome());
       assertNotNull(createdUser.getSobrenome());
       assertNotNull(createdUser.getEmail());
       assertNotNull(createdUser.getGenero());

       assertTrue(createdUser.getId() > 0);

       assertEquals("Rafael", createdUser.getNome());
       assertEquals("Santos", createdUser.getSobrenome());
       assertEquals("rafael@gmail.com", createdUser.getEmail());
       assertEquals("Masculino", createdUser.getGenero());
       assertTrue(createdUser.getEnabled());
    }

    @Test
    @Order(2)
    void criarUsuarioComOringiIncorreta() throws JsonProcessingException {

        specification = new RequestSpecBuilder()
            .addHeader(TestConfigs.HEADER_PARM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST_NOT_AUT)
            .setBasePath("/api/user/v1")
            .setPort(port)
            .addFilter(new RequestLoggingFilter(LogDetail.ALL))
            .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
            .build();

        var content = given(specification)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(user)
            .when()
            .post()
            .then()
            .statusCode(403)
            .extract()
            .body()
            .asString();

        assertEquals("Invalid CORS request", content);
    }

    @Test
    @Order(3)
    void findById() throws JsonProcessingException {
        specification = new RequestSpecBuilder()
            .addHeader(TestConfigs.HEADER_PARM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST)
            .setBasePath("/api/user/v1")
            .setPort(port)
            .addFilter(new RequestLoggingFilter(LogDetail.ALL))
            .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
            .build();

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", user.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        UserDTO createdUser = objectMapper.readValue(content, UserDTO.class);
        user = createdUser;

        assertNotNull(createdUser.getId());
        assertNotNull(createdUser.getNome());
        assertNotNull(createdUser.getSobrenome());
        assertNotNull(createdUser.getEmail());
        assertNotNull(createdUser.getGenero());

        assertTrue(createdUser.getId() > 0);

        assertEquals("Rafael", createdUser.getNome());
        assertEquals("Santos", createdUser.getSobrenome());
        assertEquals("rafael@gmail.com", createdUser.getEmail());
        assertEquals("Masculino", createdUser.getGenero());
        assertTrue(createdUser.getEnabled());
    }

    @Test
    @Order(4)
    void findByIdComOringiIncorreta() throws JsonProcessingException {
        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST_NOT_AUT)
                .setBasePath("/api/user/v1")
                .setPort(port)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", user.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(403)
                .extract()
                .body()
                .asString();
        assertEquals("Invalid CORS request", content);
    }


    private void mockUser() {

        user.setId(2L);
        user.setNome("Rafael");
        user.setSobrenome("Santos");
        user.setEmail("rafael@gmail.com");
        user.setGenero("Masculino");
        user.setEnabled(true);
    }

}