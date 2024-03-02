package book_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

//this class is used for doing the business logic
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookDTOMapper bookDTOMapper;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, BookDTOMapper bookDTOMapper,
                       BookMapper bookMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookDTOMapper = bookDTOMapper;
        this.bookMapper = bookMapper;
        this.authorRepository = authorRepository;
    }

    public BookDTO findBookById(Integer id) {
        return bookDTOMapper.apply(bookRepository.findById(id).orElse(null));

    }

    public List<BookDTO> getBooksListByName(String name){
       return bookRepository.getBooksListByBookName(name)
               .stream().map(bookDTOMapper).collect(Collectors.toList());
    }

    public BookDTO findBookByName(String bookName){
        return bookDTOMapper.apply(bookRepository.findBookByBookName(bookName));
    }

    List<BookDTO> findBooksByAuthorID(Integer id){
        return bookRepository.getBooksByAuthorID(id).stream()
                .map(bookDTOMapper).collect(Collectors.toList());
    }

    public List<BookDTO> getAllBooks(){
        return bookRepository.findAll().stream().map(bookDTOMapper).collect(Collectors.toList());
    }

    public BookDTO addBook(BookDTO book) {
        Book savedBook = bookRepository.save(bookMapper.apply(book));
        book.setBookId(savedBook.getId());
        return book;
    }

    public BookDTO updateBook(Integer id, BookDTO book) {
        Book currentBook = bookRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("book isn't found"));

        currentBook.setBookName(book.getBookName());
        currentBook.setBookDescription(book.getBookDescription());
        if(book.getAuthorName() != null){
            currentBook.setAuthorID(
                   authorRepository.getAuthorByAuthorName(book.getAuthorName()).get().getId()
            );
        }
        currentBook.setYearOfRelease(book.getYearOfRelease());

        bookRepository.save(currentBook);

        return bookDTOMapper.apply(currentBook);
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}
