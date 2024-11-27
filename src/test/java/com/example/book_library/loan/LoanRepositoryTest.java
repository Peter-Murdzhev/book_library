package com.example.book_library.loan;

import com.example.book_library.book.Book;
import com.example.book_library.reader.Reader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LoanRepositoryTest {
    @Autowired
    private LoanRepository loanRepository;

    @Test
    void findListSizeByReaderId(){
        List<Loan> loans = loanRepository.findByReaderId(1);

        assertEquals(4,loans.size());
    }

    @Test
    void checkBookNameByReaderId(){
        List<Loan> loans = loanRepository.findByReaderId(1);
        Book currentBook = loans.get(2).getBook();

        assertEquals("The traitor`s emblem", currentBook.getBookName());
    }

    @Test
    void findListSizeByBookId(){
        List<Loan> loans = loanRepository.findByBookId(3);

        assertEquals(1,loans.size());
    }

    @Test
    void checkReaderNameByBookId(){
        List<Loan> loans = loanRepository.findByBookId(34);
        Reader reader = loans.get(0).getReader();

        assertEquals("Rozana Filipova", reader.getFullname());
    }

    @Test
    void returnDateNotExpired(){
        List<Loan> loans = loanRepository.findByBookId(1);

        for (Loan loan : loans){
            assertTrue(loan.getDateOfReturn().isAfter(LocalDate.now()));
        }
    }

    @Test
    void isReturned(){
        List<Loan> loans = loanRepository.findByBookId(6);

        assertTrue(loans.get(0).isReturned());
    }

    @Test
    void notReturned(){
        List<Loan> loans = loanRepository.findByBookId(35);

        assertFalse(loans.get(0).isReturned());
    }

    @Test
    void isEmpty(){
        List<Loan> loans = loanRepository.findByReaderId(500);

        assertTrue(loans.isEmpty());
    }
}