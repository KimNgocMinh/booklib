package com.minh.booklib.dto;

import com.minh.booklib.model.Book;
import com.minh.booklib.model.Role;
import com.minh.booklib.model.User;

import java.util.List;

public class UserDTO {
    private String username;

    private String email;

    private Role role;

    private List<Book> favoriteBooks;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.favoriteBooks = user.getFavoriteBooks();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
