package com.example.book_library.overdue_loan_finder;

import com.example.book_library.loan.Loan;
import com.example.book_library.loan.LoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OverdueLoanFinderTest {
    @Autowired
    private LoanService loanService;

    @Test
    void findOverdueBooks(){
        List<Loan> overdueLoans = loanService.findAll().stream()
                .filter(loan -> loan.getDateOfReturn().isBefore(LocalDate.now()) &&
                        !loan.isReturned())
                .collect(Collectors.toList());

//        assertEquals(overdueLoans.size(),1);
        assertEquals(overdueLoans.get(0).getId(),9);
    }

}