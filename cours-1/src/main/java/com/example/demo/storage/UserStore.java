package com.example.demo.storage;

import com.example.demo.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserStore implements IUserStore {

    private List<User> db;

    public UserStore() {
        this.db = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        this.db.add(user);
    }

    @Override
    public int size() {
        return this.db.size();
    }

    @Override
    public List<User> getUsers() {
        return this.db;
    }
}
