package com.akshar.expensetrackerapi.Controller;

import com.akshar.expensetrackerapi.Entity.User;
import com.akshar.expensetrackerapi.Entity.UserModel;
import com.akshar.expensetrackerapi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<String>("User is loged in", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> save(@Valid @RequestBody UserModel userModel) {
        return new ResponseEntity<User>(userService.createUser(userModel),HttpStatus.CREATED);
    }

}
