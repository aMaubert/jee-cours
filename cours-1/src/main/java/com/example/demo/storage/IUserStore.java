package com.example.demo.storage;

import com.example.demo.models.User;

import java.util.List;

public interface IUserStore {
    void addUser(User user);
    int size();
    List<User> getUsers();
}
