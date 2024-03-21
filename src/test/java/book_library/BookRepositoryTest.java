package book_library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void findBookByName(){
       Book currentBook = bookRepository.findBookByBookName("monster");
       String  expectedBookName = "monster";

       assertEquals(expectedBookName,currentBook.getBookName().toLowerCase());
    }

    @Test
    void addBookAndFindItByName(){
        Book newBook = bookRepository.findBookByBookName("monster");

        String wantedBook = "monster";

        assertEquals(wantedBook,newBook.getBookName().toLowerCase());
    }
}