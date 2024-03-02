package book_library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> getBooksListByBookName(String bookName);

    List<Book> getBooksByAuthorID(Integer id);

    @Query(value = "SELECT * FROM books WHERE book_name=? LIMIT 1",
    nativeQuery = true)
    Book findBookByBookName(String bookName);
}
