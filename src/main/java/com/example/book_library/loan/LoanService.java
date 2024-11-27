package com.example.book_library.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public Loan findById(Integer id){
        return loanRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("loan isnt found"));
    }

    public List<Loan> findAll(){
        return loanRepository.findAll();
    }

    public List<Loan> findLoansByBookId(Integer bookId){
        return loanRepository.findByBookId(bookId);
    }

    public List<Loan> findLoansByReaderId(Integer readerId){
        return loanRepository.findByReaderId(readerId);
    }

    public Loan saveLoan(Loan loan){
        return loanRepository.save(loan);
    }

    public Loan alterLoan(Integer id, Loan loan){
        Loan currentLoan = loanRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("loan isn't found"));

        currentLoan.setReader(loan.getReader());
        currentLoan.setBook(loan.getBook());
        currentLoan.setDateOfLoan(loan.getDateOfLoan());
        currentLoan.setDateOfReturn(loan.getDateOfReturn());
        currentLoan.setReturned(loan.isReturned());

        return loanRepository.save(currentLoan);
    }

    public void deleteLoan(Integer id){
        loanRepository.deleteById(id);
    }

}
