package com.minh.booklib.model;


import java.io.Serializable;

public class CategoryId implements Serializable {
    private final String name;

    private final Long bookId;

    public CategoryId(String name, Long bookId) {
        this.name = name;
        this.bookId = bookId;
    }

}
