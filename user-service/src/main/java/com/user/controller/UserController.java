package com.user.controller;

import com.user.dto.UserRequest;
import com.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/digi-fin-pocket/users")
public class UserController {

    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CURD operations will be implemented here
    @GetMapping("/{id}")
    public UserRequest getUsers(@PathVariable Long id) {
        log.info("Fetching user by id: {}", id);
        return userService.getUserById(id);
    }

    @PostMapping
    public UserRequest createUser(@RequestBody UserRequest user) {
        log.info("Creating user: {}", user);
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserRequest updateUser(@PathVariable Long id, @RequestBody UserRequest user) {
        log.info("Updating user: {}", user);
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("Deleting user: {}", id);
        userService.deleteUser(id);
    }

    @GetMapping("/userName/{userName}")
    public UserRequest getUserByUserName(@PathVariable String userName) {
        log.info("Fetching user by userName: {}", userName);
        return userService.getUserByUserName(userName);
    }

    @GetMapping("/email/{email}")
    public UserRequest getUserByEmail(@PathVariable String email) {
        log.info("Fetching user by email: {}", email);
        return userService.getUserByEmail(email);
    }

    @GetMapping("/phoneNumber/{phoneNumber}")
    public UserRequest getUserByPhoneNumber(@PathVariable String phoneNumber) {
        log.info("Fetching user by phoneNumber: {}", phoneNumber);
        return userService.getUserByPhoneNumber(phoneNumber);
    }

    @GetMapping("/role/{role}")
    public UserRequest getUserByRole(@PathVariable String role) {
        log.info("Fetching user by role: {}", role);
        return userService.getUserByRole(role);
    }

}
