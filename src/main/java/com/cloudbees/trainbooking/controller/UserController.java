package com.cloudbees.trainbooking.controller;

import com.cloudbees.trainbooking.entity.User;
import com.cloudbees.trainbooking.exception.NotFoundException;
import com.cloudbees.trainbooking.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "API for - #4",
            description = "4. User should include first and last name, email address")
    public ResponseEntity<User> getUser(@PathVariable int id) throws NotFoundException {
        var user = service.get(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
