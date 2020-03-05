package com.example.webdemo.models;

import com.example.webdemo.models.dto.UserCreateDTO;
import lombok.Data;

@Data
public class User {
    private String  id;
    private String name;

    public User(String name) {
        this.name = name;
    }

    public User(UserCreateDTO user) {
        this.name = user.getName();
    }
}
