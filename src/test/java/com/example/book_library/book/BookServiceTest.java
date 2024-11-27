package com.example.book_library.book;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    void findBookByNameTidelands(){
        List<Book> booksByName = bookService.findBooksByName("tidelands");

        assertTrue(booksByName.get(0).getBookName().equals("Tidelands"));
    }

    @Test
    void findBookByNameHouseOfDark(){
        List<Book> booksByName = bookService.findBooksByName("house of dark");

        assertTrue(booksByName.get(0).getBookName().equals("House of dark"));
    }

    @Test
    void findBooksByPhilipaGregory(){
        List<Book> booksByPhilipaGregory = bookService.findBooksByAuthorName("Philipa Gregory");

        for(Book book : booksByPhilipaGregory){
            assertEquals("Philipa Gregory",book.getAuthorName());
        }
    }

    @Test
    void findBooksByDavidWhiteHouse(){
        List<Book> booksByDavidWhiteHouse = bookService.findBooksByAuthorName("David Whitehouse");

       assertEquals(1,booksByDavidWhiteHouse.size());
    }

}