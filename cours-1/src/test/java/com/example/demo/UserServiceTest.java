package com.example.demo;

import com.example.demo.config.UserConfig;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.storage.UserStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserServiceTest {

    private UserService userService;
    private UserConfig userConfig;
    private UserStore userStore;

    @BeforeEach
    void setup() {
        userConfig = new UserConfig(2);
        userStore = new UserStore();
        this.userService = new UserService(userStore, userConfig);
    }

    @Nested
    class NominalCase {
        // special cases
    }

    @Test
    void should_create_a_new_user() {
        // given -> préparation du test
        User user = new User("One");

        // when -> cadre d'execution
        userService.createUser(user);

        // then -> je devrai avoir le résultat observer
        assertThat(userService.getUsers())
                .hasSize(1)
                .extracting(User::getName)
                .contains("One");
    }

    @Test
    void should_throw_exception_when_too_many_users_are_inserted() {
        userConfig = new UserConfig(0);
        userService = new UserService(userStore, userConfig);

//        try{
//            userService.createUser( new User("should_throw"));
//            fail("should have throw IllegalStateException");
//        } catch (IllegalStateException ex) {
//            assertThat(ex.getMessage()).isEqualTo("");
//        }

        assertThatThrownBy(() -> userService.createUser(new User("should_throw")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Too many users");
    }
}
