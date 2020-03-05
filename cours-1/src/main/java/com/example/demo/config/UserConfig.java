package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
//@ConfigurationProperties(prefix = "app" )
public class UserConfig {

    /**
     * Define max Users load
     */
    //app.max-users
    private int maxUser;

    /**
     * Define something else
     */
    private String test;

    @Autowired
    public UserConfig( @Value("${app.config.user.max}") int maxUser) {
        this.maxUser = maxUser;
        System.out.println("maxUser is : " + maxUser);
    }
}
