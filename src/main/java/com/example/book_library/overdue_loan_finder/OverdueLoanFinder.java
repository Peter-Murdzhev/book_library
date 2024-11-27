package com.example.book_library.overdue_loan_finder;

import com.example.book_library.email.BookOverdueEmail;
import com.example.book_library.loan.Loan;
import com.example.book_library.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Async
public class OverdueLoanFinder {
    @Autowired
    private LoanService loanService;

    @Autowired
    private BookOverdueEmail bookOverdueEmail;

//    @Scheduled(cron = "0 0 12 * * ?")
    @Scheduled(fixedRate = 1000 * 60 * 60 * 12)
    public void findOverdueLoans(){
        //The IDE suggests to use only .toList() but that list is totally unmodifiable
        //So I prefer not to use it
        List<Loan> overdueLoans = loanService.findAll().stream()
                .filter(loan -> loan.getDateOfReturn().isBefore(LocalDate.now()) &&
                        !loan.isReturned())
                .collect(Collectors.toList());

        for(Loan currentLoan : overdueLoans){
            bookOverdueEmail.sendOverdueEmail(currentLoan);
        }
    }
}
