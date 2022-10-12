package com.minh.booklib.service;

import com.minh.booklib.model.Book;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface BookService {

    List<Book> getBookByAuthor(String author);

    List<Book> getBookByTitle(String title);

    List<Book> getBookByPublishDate(LocalDate publishDate);

}
