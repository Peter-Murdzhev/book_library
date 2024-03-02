package book_library;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int id;

    @Column(
            name = "author_name",
            unique = true
    )
    private String authorName;

    @OneToMany()
    @JoinColumn(name = "author_id")
    private List<Book> booksByAuthor;

    @Autowired
    public Author (String authorName){
        this.authorName = authorName;
        booksByAuthor = new ArrayList<>();
    }

    public Author(){}

    public int getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        if(authorName != null){
            this.authorName = authorName;
        }
    }

    public List<Book> getBooksByAuthor(){
        return booksByAuthor;
    }
}
