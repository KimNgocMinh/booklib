package com.minh.booklib.controller;

import com.minh.booklib.exception.InvalidAccountException;
import com.minh.booklib.model.Book;
import com.minh.booklib.model.User;
import com.minh.booklib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User unknown) {
        userService.createUser(unknown);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        userService.authentication(username, password);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully");
    }

    @PostMapping("/favor")
    public List<Book> getFavoriteBook(@RequestBody String username) {
        return userService.getAllFavoritebook(username);
    }

    @PostMapping("/like")
    public ResponseEntity<String> insertFavoriteBook(@RequestParam String username, @RequestParam Long bookId) {
        userService.insertFavoriteBook(username, bookId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully");
    }

    @DeleteMapping("/dislike")
    public ResponseEntity<String> removeFavoriteBook(@RequestParam String username, @RequestParam Long bookId) {
        userService.removeFavoriteBook(username, bookId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully");
    }

    @ExceptionHandler(InvalidAccountException.class)
    public ResponseEntity<String> handleInvalidAcountException(InvalidAccountException exception) {
        return ResponseEntity.status(exception.getCode()).body(exception.getMessage());
    }
}
