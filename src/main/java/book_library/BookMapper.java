package book_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BookMapper implements Function<BookDTO, Book> {
    private final AuthorRepository authorRepository;

    @Autowired
    public BookMapper (AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public Book apply(BookDTO bookDTO) {
        Author author = authorRepository.getAuthorByAuthorName(bookDTO.getAuthorName())
                .orElse(null);

        if(author == null){
            author = new Author(bookDTO.getAuthorName());
            authorRepository.save(author);
        }

        return new Book(
                0,
                bookDTO.getBookName(),
                author.getId(),
                bookDTO.getBookDescription(),
                bookDTO.getYearOfRelease()
        );
    }


}
