package com.example.book_library.loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Integer> {
    List<Loan> findByReaderId(Integer readerId);
    List<Loan> findByBookId(Integer bookId);
}
