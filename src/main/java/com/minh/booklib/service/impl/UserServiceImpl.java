package com.minh.booklib.service.impl;

import com.minh.booklib.exception.InvalidEmailException;
import com.minh.booklib.exception.InvalidPasswordException;
import com.minh.booklib.exception.InvalidUsernameException;
import com.minh.booklib.model.Role;
import com.minh.booklib.model.User;
import com.minh.booklib.repository.UserRepository;
import com.minh.booklib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void createUser(User unknown) {
        if (!checkValidUsername(unknown.getUsername())) {
            throw new InvalidUsernameException("Username must have at least 8 character");
        }
        if (!checkValidPassword(unknown.getPassword())) {
            throw new InvalidPasswordException("must have special characters, uppercase, lowercase, digit and be at least 8 characters long");
        }
        if (unknown.getEmail() != null) {
            if (!checkvalidEmail(unknown.getEmail())) {
                throw new InvalidEmailException("Invalid Email");
            }
        }
        if(userRepo.findByUsername(unknown.getUsername()) != null) {
            throw new InvalidUsernameException("Account is already exist");
        }
        if (userRepo.findByEmail(unknown.getEmail()) != null) {
            throw new InvalidEmailException("Email is already exist");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        unknown.setRole(Role.USER);
        unknown.setPassword(bCryptPasswordEncoder.encode(unknown.getPassword()));
        userRepo.save(unknown);
    }

    public boolean checkValidUsername(String username) {
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z]\\w{7,49}$");
        return Pattern.matches(usernamePattern.pattern(), username);
    }

    public boolean checkValidPassword(String password) {
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*./+-~])(?!.*\\s).{7,49}$");
        return Pattern.matches(passwordPattern.pattern(), password);
    }

    public boolean checkvalidEmail(String email) {
        Pattern emailPattern = Pattern.compile("^\\w+@\\w+(\\.\\w+)+$");
        return Pattern.matches(emailPattern.pattern(), email);
    }
}
