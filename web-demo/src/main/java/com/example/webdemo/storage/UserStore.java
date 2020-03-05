package com.example.webdemo.storage;

import com.example.webdemo.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserStore {

    private List<User> db;

    public UserStore() {
        this.db = new ArrayList<>();
    }

    public void addUser(User user) {
        this.db.add(user);
    }

    public int size() {
        return this.db.size();
    }

    public List<User> getUsers() {
        return this.db;
    }
}
