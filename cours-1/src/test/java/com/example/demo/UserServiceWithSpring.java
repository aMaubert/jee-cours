package com.example.demo;

import com.example.demo.config.UserConfig;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.storage.UserStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestPropertySource(properties = "app.config.user.max=3")
@ExtendWith(SpringExtension.class)
@Import({
        UserService.class,
        UserStore.class,
        UserConfig.class
})

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceWithSpring {

    @Autowired
    private UserService sut;

    @Test
    void should_create_user() {
        sut.createUser(new User("One"));
        assertThat(sut.getUsers()).hasSize(1);
    }

    @Test
    void shoild_throw_exception_when_reached_maximum_users() {
        sut.createUser(new User("One"));
        sut.createUser(new User("Two"));
        sut.createUser(new User("Three"));
        assertThatThrownBy(() -> sut.createUser(new User("Fourth") ))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Too many users");
    }
}
