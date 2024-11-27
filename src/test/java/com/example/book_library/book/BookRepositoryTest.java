package com.example.book_library.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void getBookMonster(){
        List<Book> booksByName = bookRepository.getBookByBookName("Monster");

        assertTrue(booksByName.get(0).getBookName().equals("Monster"));
    }

    @Test
    void getBookIt(){
        List<Book> booksByName = bookRepository.getBookByBookName("it");

        assertEquals(booksByName.size(),1);
    }

    @Test
    void getBooksByStephenKing(){
        List<Book> booksByStephenKing = bookRepository.getBookByAuthorName("Stephen King");

        for(Book book : booksByStephenKing){
            assertEquals("Stephen King", book.getAuthorName());
        }
    }

    @Test
    void getBooksByBoydMorrison(){
        List<Book> booksByStephenKing = bookRepository.getBookByAuthorName("Boyd Morrison");

        assertEquals(1,booksByStephenKing.size());
    }

    @Test
    void getBooksByJurado(){
        List<Book> booksByStephenKing = bookRepository.getBookByAuthorName("Juan Gomes Jurado");

        assertEquals(3,booksByStephenKing.size());
    }

}