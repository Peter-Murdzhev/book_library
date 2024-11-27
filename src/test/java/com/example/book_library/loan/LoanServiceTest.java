package com.example.book_library.loan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LoanServiceTest {
    @Autowired
    private LoanService loanService;

    @Test
    void findLoansByBookIdCheckBookName(){
        List<Loan> loans = loanService.findLoansByBookId(1);

        for(Loan loan : loans){
            assertEquals("Green Mile", loan.getBook().getBookName());
        }
    }

    @Test
    void findLoansByBookIdDueDateNotExpired(){
        List<Loan> loans = loanService.findLoansByBookId(3);

        for(Loan loan : loans){
            assertTrue(loan.getDateOfReturn().isAfter(LocalDate.now()));
        }
    }

    @Test
    void findLoansByReaderIdCheckReaderFullName(){
        List<Loan> loans = loanService.findLoansByReaderId(8);

        for(Loan loan : loans){
            assertEquals("Elena Georgieva", loan.getReader().getFullname());
        }
    }
}