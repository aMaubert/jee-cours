package com.example.webdemo.models.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserCreateDTO {

    @NotNull
    private String name;
}
