package com.example.demo;

import com.example.demo.config.UserConfig;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.storage.IUserStore;
import com.example.demo.storage.UserStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        DemoApplication.testUserService();

        DatabaseConnectionWithSpring databaseConnectionWithSpring = context.getBean(DatabaseConnectionWithSpring.class);
        System.out.println(databaseConnectionWithSpring.toString());

    }
    private static void testUserService() {
        IUserStore userStore = new UserStore();
        UserConfig userConfig = new UserConfig(5);
        UserService userService = new UserService(userStore, userConfig);

        User user = new User("Allan");
        User user2 = new User("Allan");
        User user3 = new User("Allan");
        User user4 = new User("Allan");
        User user5 = new User("Allan");
        User user6 = new User("Allan");
        User user7 = new User("Allan");

        try{
            userService.createUser(user);
            userService.createUser(user2);
            userService.createUser(user3);
            userService.createUser(user4);
            userService.createUser(user5);
            userService.createUser(user6);
            System.out.println(userService.getUsers().toString());
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}


//Singleton example
class DatabaseConnection {

    private static final DatabaseConnection instance = new DatabaseConnection();

    private DatabaseConnection() {

    }

    public static DatabaseConnection getInstance() {
        return instance;
    }
}

//Singleton example with Spring
@Component
class DatabaseConnectionWithSpring {

}
