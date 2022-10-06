package com.minh.booklib.controller;

import com.minh.booklib.exception.InvalidEmailException;
import com.minh.booklib.exception.InvalidPasswordException;
import com.minh.booklib.exception.InvalidUsernameException;
import com.minh.booklib.model.User;
import com.minh.booklib.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            userService.createUser(user);
        } catch (InvalidUsernameException usernameException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usernameException.getMessage());
        } catch (InvalidPasswordException passwordException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(passwordException.getMessage());
        } catch (InvalidEmailException emailException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(emailException.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Successfully");
    }
}
