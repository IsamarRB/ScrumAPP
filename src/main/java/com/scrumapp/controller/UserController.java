package com.scrumapp.controller;

import com.scrumapp.model.User;
import com.scrumapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin

public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);

    }

    @GetMapping(path = "/")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @GetMapping(path = "/{id}")
    public Optional<User> getDonationId(@PathVariable int id){
        return userService.getUserById(id);

    }

    @PutMapping(path = "/{id}")
    public void  updateUser(@RequestBody User user, @PathVariable int id){
        userService.updateUser(user, id);

    }

    @DeleteMapping(path = "/{id}")
    public String deleteDonationById(@PathVariable int id) {
        boolean ok = userService.deleteUser(id);

        if (ok) {
            return "User with id " + id + " was deleted";
        } else {
            return "User with id " + id + " not found";
        }
    }
}