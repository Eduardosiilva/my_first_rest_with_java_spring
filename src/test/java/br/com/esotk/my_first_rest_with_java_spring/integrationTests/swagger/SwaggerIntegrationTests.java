package br.com.esotk.my_first_rest_with_java_spring.integrationTests.swagger;

import br.com.esotk.my_first_rest_with_java_spring.config.TestConfigs;
import br.com.esotk.my_first_rest_with_java_spring.integrationTests.testsIntegration.AbstractIntegrationTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SwaggerIntegrationTests extends AbstractIntegrationTests {

    @LocalServerPort
    private int port;

    @Test
	void validaDocumentacaoSwagger() {

        var content = given()
                .basePath("/swagger-ui/index.html")
                    .port(port)
                .when()
                    .get()
                .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .asString();
        Assertions.assertTrue(content.contains("Swagger UI"));
	}

}
