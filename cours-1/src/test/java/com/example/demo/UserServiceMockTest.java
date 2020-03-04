package com.example.demo;

import com.example.demo.config.UserConfig;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.storage.UserStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class UserServiceMockTest {

    private UserService userService;
    private UserConfig userConfig;
    private UserStore userStore;

    @BeforeEach
    void setup() {
        userStore = mock(UserStore.class);
        userConfig = mock(UserConfig.class);
        userService = new UserService(userStore, userConfig);

        given(userConfig.getMaxUser()).willReturn(1);
        given(userStore.size()).willReturn(0);

    }

    @Test
    void should_create_a_new_user() {
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        userService.createUser(new User("One"));
        verify(userStore, times(1)).addUser(userArgumentCaptor.capture());
        assertThat(userArgumentCaptor.getValue()).extracting(User::getName).isEqualTo("One");
    }

}
