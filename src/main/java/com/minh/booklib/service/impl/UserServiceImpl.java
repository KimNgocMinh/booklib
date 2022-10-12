package com.minh.booklib.service.impl;

import com.minh.booklib.exception.InvalidAccountException;
import com.minh.booklib.model.Book;
import com.minh.booklib.model.Role;
import com.minh.booklib.model.User;
import com.minh.booklib.repository.BookRepository;
import com.minh.booklib.repository.UserRepository;
import com.minh.booklib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    private final BookRepository bookRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepo, BookRepository bookRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public void createUser(User unknown) {
        if (!checkValidUsername(unknown.getUsername())) {
            throw new InvalidAccountException(400,"username must be between 8 and 50 characters and contain no special characters");
        }
        if (!checkValidPassword(unknown.getPassword())) {
            throw new InvalidAccountException(400,"Password must have special characters, uppercase, lowercase, digit and be at least 8 characters long");
        }
        if (unknown.getEmail() != null) {
            if (!checkValidEmail(unknown.getEmail())) {
                throw new InvalidAccountException(400,"Invalid Email");
            }
        }
        if(userRepo.findByUsername(unknown.getUsername()) != null) {
            throw new InvalidAccountException(400,"Account is already exist");
        }
        if (userRepo.findByEmail(unknown.getEmail()) != null) {
            throw new InvalidAccountException(400,"Email is already exist");
        }
        unknown.setRole(Role.USER);
        unknown.setPassword(encoder.encode(unknown.getPassword()));
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

    public boolean checkValidEmail(String email) {
        Pattern emailPattern = Pattern.compile("^\\w+@\\w+(\\.\\w+)+$");
        return Pattern.matches(emailPattern.pattern(), email);
    }

    @Override
    public void authentication(String username, String password) {
        User userDB = userRepo.findByUsername(username);
        if (userDB == null) {
            throw new InvalidAccountException(401,"Account does not exist");
        }
        if (!encoder.matches(password, userDB.getPassword())) {
            throw new InvalidAccountException(401,"Incorrect password");
        }

    }

    @Override
    public List<Book> getAllFavoritebook(String username) {
        User user = userRepo.findByUsername(username);
        return user.getFavoriteBooks();
    }

    @Override
    public void insertFavoriteBook(String username, Long bookId) {

        User user = userRepo.findByUsername(username);
        Book book = bookRepo.findByBookId(bookId);

        user.getFavoriteBooks().add(book);
        book.getUsers().add(user);
        userRepo.save(user);
    }

    @Override
    public void removeFavoriteBook(String username, Long bookId) {
        User user = userRepo.findByUsername(username);
        Book book = bookRepo.findByBookId(bookId);

        user.getFavoriteBooks().remove(book);
        book.getUsers().remove(user);
        userRepo.save(user);
    }

}
