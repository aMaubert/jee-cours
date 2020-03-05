package com.example.webdemo;

import com.example.webdemo.models.User;
import com.example.webdemo.models.dto.UserCreateDTO;
import com.example.webdemo.models.dto.UserDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserResourceE2ETest {

    //@Value("${server.port}")
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void should_list_all_users() {
        List<UserDTO> users =
                given()
                    .log().all()
                .when()
                    .get("/users")
                .then()
                    .extract()
                    .jsonPath()
                    .getList(".", UserDTO.class);
//        get("/users")
//                .then()
//                .statusCode(200);
        assertThat(users).hasSize(2);
    }

    @Test
    void should_create_a_new_user() {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setName("test");
        given()
            .contentType(ContentType.JSON)
            .body(userCreateDTO)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .header("Location", notNullValue());
        get("/users" ).then().body("$", hasSize(3));
    }
}
