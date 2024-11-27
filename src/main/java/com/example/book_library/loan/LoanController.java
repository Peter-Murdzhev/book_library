package com.example.book_library.loan;

import com.example.book_library.book.Book;
import com.example.book_library.book.BookService;
import com.example.book_library.reader.Reader;
import com.example.book_library.reader.ReaderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LoanController {
    @Autowired
    private LoanService loanService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ReaderService readerService;

    @PostMapping(value = "/loan/create")
    public ResponseEntity<?> createLoan(@RequestBody @Valid LoanRequest request,
                                        BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            HashMap<String, String> errors = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()){
                errors.put(error.getField(),error.getDefaultMessage());
            }

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try{
            Book book = bookService.findBookById(request.bookId());
            Reader reader = readerService.findReaderById(request.readerId());

            if(book.getBooksCount() <= 0){
                return new ResponseEntity<>("all books are given", HttpStatus.BAD_REQUEST);
            }

            Loan loan = new Loan();
            loan.setBook(book);
            loan.setReader(reader);
            loan.setDateOfLoan(LocalDate.now());
            loan.setDateOfReturn(LocalDate.now().plusMonths(3));

            book.setBooksCount(book.getBooksCount() - 1);
            bookService.alterBook(book.getId(),book);

            return ResponseEntity.ok(loanService.saveLoan(loan));
        }catch (IllegalArgumentException iae){
            return new ResponseEntity<>("book ID or reader ID is incorrect", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "loan/return/{id}")
    public ResponseEntity<?> returnBook(@PathVariable Integer id){
        try{
            Loan loan = loanService.findById(id);
            if(loan.isReturned()){
                throw new IllegalStateException("The book is already returned");
            }else{
                loan.setReturned(true);
            }

            Book book = loan.getBook();
            book.setBooksCount(book.getBooksCount() + 1);
            bookService.alterBook(book.getId(), book);

            return ResponseEntity.ok(loanService.alterLoan(id, loan));
        }catch (IllegalArgumentException iae){
            return new ResponseEntity<>("loan isn't found", HttpStatus.BAD_REQUEST);
        }catch (IllegalStateException ise){
            return new ResponseEntity<>("The book is already returned", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/loan/get/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(loanService.findById(id));
        }catch (IllegalArgumentException iae){
            return new ResponseEntity<>("loan isn't found", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/loan/getbybookid/{id}")
    public ResponseEntity<List<Loan>> getLoansByBookId(@PathVariable Integer id){
        return ResponseEntity.ok(loanService.findLoansByBookId(id));
    }

    @GetMapping(value = "/loan/getbyreaderid/{id}")
    public ResponseEntity<List<Loan>> getLoansByReaderId(@PathVariable Integer id){
        return ResponseEntity.ok(loanService.findLoansByReaderId(id));
    }

    @GetMapping(value = "/loan/returndate/expired")
    public ResponseEntity<List<Loan>> getDueDateLoans(){
        return ResponseEntity.ok(loanService.findAll().stream()
                .filter(loan -> loan.getDateOfReturn().isBefore(LocalDate.now())
                && !loan.isReturned()).collect(Collectors.toList()));
    }

//    @PutMapping(value = "/loan/alter/{id}")
//    public ResponseEntity<Loan> alterLoan(@PathVariable Integer id, @RequestBody Loan loan){
//        return ResponseEntity.ok(loanService.alterLoan(id,loan));
//    }

    @DeleteMapping(value = "/loan/delete/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable Integer id){
        loanService.deleteLoan(id);
        return ResponseEntity.ok("loan deleted");
    }

}
