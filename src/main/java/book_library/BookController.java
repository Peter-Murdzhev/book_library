package book_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//here we set the API endpoints
@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/book/getbyid/{id}")
    public BookDTO findBookById(@PathVariable Integer id) {
        return bookService.findBookById(id);
    }

    @GetMapping(value = "/book/getbookslistbyname/{name}")
    public List<BookDTO> getBooksListByBookName(@PathVariable String name){
        return bookService.getBooksListByName(name);
    }

    @GetMapping(value = "/book/getbyname/{bookName}")
    public BookDTO findBookByName(@PathVariable String bookName){
        return bookService.findBookByName(bookName);
    }

    @GetMapping(value = "/book/getbyauthorid/{id}")
    public List<BookDTO> getBooksByAuthorID(@PathVariable Integer id){
        return bookService.findBooksByAuthorID(id);
    }

    @GetMapping(value = "/book/getall")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping(value = "/book/add")
    public BookDTO addBook(@RequestBody BookDTO book) {
        return bookService.addBook(book);
    }

    @PutMapping(value = "/book/update/{id}")
    public BookDTO updateBook(@PathVariable Integer id, @RequestBody BookDTO book) {
        return bookService.updateBook(id,book);
    }

    @DeleteMapping(value = "/book/delete/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }
}
