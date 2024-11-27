package com.example.book_library.book;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/book/getbyid/{id}")
    public ResponseEntity<?> findBookById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(bookService.findBookById(id));
        }catch (IllegalArgumentException iae){
            return new ResponseEntity<>("required book isn't found.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/book/getbyname/{name}")
    public List<Book> getBooksByBookName(@PathVariable String name){
        return bookService.findBooksByName(name);
    }

    @GetMapping(value = "/book/getbyauthorname/{authorName}")
    public List<Book> getBooksByAuthorName(@PathVariable String authorName){
        return bookService.findBooksByAuthorName(authorName);
    }

    @GetMapping(value = "/book/getall")
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @PostMapping(value = "/book/add")
    public ResponseEntity<?> addBook(@RequestBody @Valid Book book, BindingResult bindingResult) {
        //find how to work out internal error 500 when not nullable field is null
        if(bindingResult.hasErrors()){
            HashMap<String,String> errors = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()){
                errors.put(error.getField(),error.getDefaultMessage());
            }

            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }else{
            return ResponseEntity.ok(bookService.addBook(book));
        }
    }

    @PutMapping(value = "/book/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Integer id, @RequestBody Book book) {
        try{
            return ResponseEntity.ok(bookService.alterBook(id, book));
        }catch (IllegalArgumentException iae){
            return new ResponseEntity<>("required book isn't found.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/book/delete/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return "book deleted.";
    }
}
