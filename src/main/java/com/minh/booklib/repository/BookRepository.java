package com.minh.booklib.repository;

import com.minh.booklib.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book,Long>, QueryByExampleExecutor<Book> {
    @Query(value = "from Book where id = ?1")
    Book findByBookId(Long bookId);

    List<Book> findAllByAuthor(String author);

    List<Book> findAllByTitle(String title);

    List<Book> findAllByPublishDate(LocalDate publishDate);

}
