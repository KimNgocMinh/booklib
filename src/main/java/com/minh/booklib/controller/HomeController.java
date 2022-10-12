package com.minh.booklib.controller;

import com.minh.booklib.dto.UserDTO;
import com.minh.booklib.model.User;
import com.minh.booklib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/books/all")
    public String book() {
        return "book";
    }

}
