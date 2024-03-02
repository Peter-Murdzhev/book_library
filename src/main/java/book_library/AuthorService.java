package book_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookDTOMapper bookDTOMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository,
    BookDTOMapper bookDTOmapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookDTOMapper = bookDTOmapper;
    }

    public Author findAuthor(Integer id){
        return authorRepository.findById(id).orElse(null);
    }

    public String getAuthorName(Integer id){
        Author author =  authorRepository.findById(id).orElse(null);
        return author != null ? author.getAuthorName() : null;
    }

    public  Author findAuthorByName(String authorName){
        return authorRepository.getAuthorByAuthorName(authorName).orElse(null);
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }

    public Author updateAuthor(Integer id, Author author){
        Author currentAuthor = authorRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("author doesn't exist in the database."));

        currentAuthor.setAuthorName(author.getAuthorName());

        return authorRepository.save(currentAuthor);
    }

    public void deleteAuthor(Integer id){
        authorRepository.deleteById(id);
    }

//    public List<BookDTO> getBooksWrittenByAuthor(String authorName){
//        Author currentAuthor = authorRepository.getAuthorByAuthorName(authorName);
//
//        return bookRepository.findAll().stream()
//                .filter(b -> b.getAuthorID() == currentAuthor.getId())
//                .map(bookDTOMapper).collect(Collectors.toList());
//    }

    public List<BookDTO> getBooksByAuthor(String authorName){
        Author author = authorRepository.getAuthorByAuthorName(authorName).orElse(null);



        return author == null ? null : author.getBooksByAuthor().stream().map(bookDTOMapper).collect(Collectors.toList());
    }
}
