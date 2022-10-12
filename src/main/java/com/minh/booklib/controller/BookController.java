package com.minh.booklib.controller;

import com.minh.booklib.model.Book;
import com.minh.booklib.service.BookService;
import com.minh.booklib.service.impl.BookServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/get-by-title/{title}")
    public List<Book> getBookByTitle(@PathVariable String title) {
        return bookService.getBookByTitle(title);
    }

    @GetMapping("/get-by-author/{author}")
    public List<Book> getBookByAuthor(@PathVariable String author) {
        return bookService.getBookByAuthor(author);
    }

    @GetMapping("get-by-publish-date/{date}")
    public List<Book> getBookByPublishDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return bookService.getBookByPublishDate(date);
    }



}
