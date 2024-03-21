package book_library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void getBookByName(){
        String name = "monster";

        BookDTO book =  bookService.findBookByName(name);

        assertEquals(name,book.getBookName().toLowerCase());
    }
}