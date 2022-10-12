package com.minh.booklib.service.impl;

import com.minh.booklib.model.Book;
import com.minh.booklib.repository.BookRepository;
import com.minh.booklib.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepo;

    @Autowired
    public BookServiceImpl(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> getBookByAuthor(String author) {
        return bookRepo.findAllByAuthor(author);
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        return bookRepo.findAllByTitle(title);
    }

    @Override
    public List<Book> getBookByPublishDate(LocalDate publishDate) {
        return bookRepo.findAllByPublishDate(publishDate);
    }
}
