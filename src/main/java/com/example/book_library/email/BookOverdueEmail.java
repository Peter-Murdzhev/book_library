package com.example.book_library.email;

import com.example.book_library.BookLibraryApplication;
import com.example.book_library.book.Book;
import com.example.book_library.loan.Loan;
import com.example.book_library.reader.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class BookOverdueEmail {
    //async requires to inject the interface instead of the actual class service
    //spring boot will find and inject the implementation automatically
    @Autowired
    private EmailSender emailSender;

    public void sendOverdueEmail(Loan loan){
        Reader reader = loan.getReader();
        Book book = loan.getBook();

        String to = reader.getEmail();

        String message = String.format("Dear %s,\n\nWe would like to announce you " +
                "that the following book taken from us is overdue:\n\nBook: %s\nAuthor: %s\n" +
                        "Return date: %s\n\nWe kindly request that you return the book as soon as possible!\n\n" +
                        "Sincerely yours: Sonya Stankova, librarian\nLibrary: %s",
                reader.getFullname(),book.getBookName(),
                book.getAuthorName(),loan.getDateOfReturn().format(
                        DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                BookLibraryApplication.BOOK_LIBRARY_NAME);

        emailSender.send(to,message);
    }
}
