package com.minh.booklib.service;

import com.minh.booklib.model.Book;
import com.minh.booklib.model.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface UserService {

    void createUser(User unknown);

    void authentication(String username, String password);

    List<Book> getAllFavoritebook(String username);

    void insertFavoriteBook(String username, Long bookId);

    void removeFavoriteBook(String username, Long bookId);

}
