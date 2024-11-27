package com.example.book_library.loan;

import com.example.book_library.book.Book;
import com.example.book_library.reader.Reader;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd.MM.YYYY")
    private LocalDate dateOfLoan;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd.MM.YYYY")
    //final date when the book should be returned
    private LocalDate dateOfReturn;

    @Column()
    private boolean isReturned;

    public Loan(Integer id, Reader reader, Book book, LocalDate dateOfLoan,
                LocalDate dateOfReturn, boolean isReturned) {
        this.id = id;
        this.reader = reader;
        this.book = book;
        this.dateOfLoan = dateOfLoan;
        this.dateOfReturn = dateOfReturn;
        this.isReturned = isReturned;
    }

    public Loan(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if(id != null){
            this.id = id;
        }
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        if(reader != null){
            this.reader = reader;
        }
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        if(book != null){
            this.book = book;
        }
    }

    public LocalDate getDateOfLoan() {
        return dateOfLoan;
    }

    public void setDateOfLoan(LocalDate dateOfLoan) {
        if(dateOfLoan != null){
            this.dateOfLoan = dateOfLoan;
        }
    }

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalDate dateOfReturn) {
        if(dateOfReturn != null){
            this.dateOfReturn = dateOfReturn;
        }
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean isReturned) {
        if(isReturned){
            this.isReturned = isReturned;
        }
    }
}
