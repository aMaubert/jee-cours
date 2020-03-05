package com.example.webdemo.services;

import com.example.webdemo.configs.UserConfig;
import com.example.webdemo.models.User;
import com.example.webdemo.storage.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserService {

    private UserStore userStore;
    private UserConfig userConfig;

    @Autowired
    public UserService(UserStore userStore, UserConfig userConfig) {
        this.userStore = userStore;
        this.userConfig = userConfig;
    }

    public void createUser(User user) {
        if( userStore.size() == this.userConfig.getMaxUser() ) {
            throw new IllegalStateException("Too many users");
        }
        user.setId(UUID.randomUUID().toString());
        this.userStore.addUser(user);
    }

    public List<User> getUsers() {
        return this.userStore.getUsers();
    }
}
