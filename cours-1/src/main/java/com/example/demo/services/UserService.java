package com.example.demo.services;


import com.example.demo.config.UserConfig;
import com.example.demo.models.User;
import com.example.demo.storage.IUserStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    private IUserStore userStore;
    private UserConfig userConfig;

    @Autowired
    public UserService(IUserStore userStore, UserConfig userConfig) {
        this.userStore = userStore;
        this.userConfig = userConfig;
    }

    public void createUser(User user) {
        if( userStore.size() == this.userConfig.getMaxUser() ) {
            throw new IllegalStateException("Too many users");
        }
        this.userStore.addUser(user);
    }

    public List<User> getUsers() {
        return this.userStore.getUsers();
    }
}
