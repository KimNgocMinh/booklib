package com.minh.booklib.model;

import javax.persistence.*;
import java.util.List;

@Entity
@IdClass(CategoryId.class)
@Table(name = "category")
public class Category {

    @Id
    private String name;

    @Id
    @Column(name = "book_id")
    private Long bookId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
