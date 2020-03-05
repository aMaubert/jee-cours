package com.example.webdemo.controllers;

import com.example.webdemo.models.User;
import com.example.webdemo.models.dto.UserCreateDTO;
import com.example.webdemo.models.dto.UserDTO;
import com.example.webdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping( path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResources {

    private final UserService userService;
    //private ObjectMapper objectMapper;

    @Autowired
    public UserResources(UserService userService) {
        this.userService = userService;
        // this.objectMapper = new ObjectMapper();
        userService.createUser(new User("Initial"));
        userService.createUser(new User("Next"));
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addUser(HttpServletRequest request, @RequestBody @Valid UserCreateDTO user, UriComponentsBuilder uriBuilder) {
        // input stream -> byte array | string --> deserialize vers UserCreateDTO
        // this.objectMapper.readValue(request.getInputStream().readAllBytes(), UserCreateDTO.class);
        User userToCreate = new User(user);
        this.userService.createUser(userToCreate);

        String userId = userToCreate.getId();
        URI uri = uriBuilder.path("/users/{userId}").buildAndExpand(userId).toUri();


//        return ResponseEntity.status(HttpStatus.CREATED)
//                            .header(HttpHeaders.LOCATION, uri )
//                            .build();
        return ResponseEntity.created(uri).build();

    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return this.userService.getUsers()
                                .stream()
                                .map(this::toUserDto)
                                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") String userId) {
        return userService.getUsers().stream()
                                .filter(user -> userId.equals(user.getId()))
                                .findFirst()
                                .map(this::toDto)
                                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<UserDTO> toDto(User user) {
        UserDTO body = new UserDTO();
        body.setName(user.getName());
        return ResponseEntity.ok(body);
    }
    private UserDTO toUserDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        return userDTO;
    }
}
