package com.akshar.expensetrackerapi.Controller;

import com.akshar.expensetrackerapi.Entity.User;
import com.akshar.expensetrackerapi.Entity.UserModel;
import com.akshar.expensetrackerapi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping(value = "/user/{id}")
    public User readUser(@PathVariable Long id){
        return userService.readUser(id);
    }

    @PutMapping(value = "/users/{id}")
    public User updateUser(@RequestBody UserModel userModel, @PathVariable Long id){
        return userService.updateUser(userModel,id);
    }

    @DeleteMapping(value = "users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
