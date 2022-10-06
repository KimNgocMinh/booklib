package com.minh.booklib.service;

import com.minh.booklib.dto.UserDTO;
import com.minh.booklib.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    void createUser(User user);
}
