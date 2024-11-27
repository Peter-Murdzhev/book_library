package com.example.book_library.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> getBookByBookName(String bookName);

    List<Book> getBookByAuthorName(String authorName);
}
