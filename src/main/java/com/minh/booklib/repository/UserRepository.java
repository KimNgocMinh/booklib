package com.minh.booklib.repository;

import com.minh.booklib.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,String> {
    User findByUsername(String username);

    @Query(value = "select email from User where email = ?1")
    String findByEmail(String email);
}
