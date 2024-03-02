package book_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BookDTOMapper implements Function<Book,BookDTO> {
    private final AuthorRepository authorRepository;

    @Autowired
    public BookDTOMapper (AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public BookDTO apply(Book book) {
        if(book == null){
            return null;
        }

        Author author = null;

        try {
            author = authorRepository.findById(book.getAuthorID()).orElse(null);
        }catch (NullPointerException npe){
            System.out.println("Author doesn't exist");
        }

        return new BookDTO(
                 book.getId(),
                 book.getBookName(),
                 author.getAuthorName(),
                 book.getBookDescription(),
                 book.getYearOfRelease()

        );
    }
}
