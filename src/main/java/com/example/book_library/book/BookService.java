package com.example.book_library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findBookById(Integer id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("book isn't found"));
    }

    public List<Book> findBooksByName(String name){
       return bookRepository.getBookByBookName(name);
    }

    List<Book> findBooksByAuthorName(String authorName){
        return bookRepository.getBookByAuthorName(authorName);
    }

    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book alterBook(Integer id, Book book) {
        Book currentBook = bookRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("book isn't found"));

        currentBook.setBookName(book.getBookName());
        currentBook.setAuthorName(book.getAuthorName());
        currentBook.setBookDescription(book.getBookDescription());
        currentBook.setYearOfRelease(book.getYearOfRelease());
        currentBook.setBooksCount(book.getBooksCount());
        currentBook.setSectionNumber(book.getSectionNumber());

        return bookRepository.save(currentBook);
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}
