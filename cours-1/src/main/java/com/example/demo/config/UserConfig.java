package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class UserConfig {

    private int maxUser;

    @Autowired
    public UserConfig( @Value("1") int maxUser) {
        this.maxUser = maxUser;
    }
}
