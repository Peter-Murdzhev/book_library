package book_library;

import jakarta.persistence.*;

//this is data class
@Entity()
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author_id")
    @JoinColumn(name = "author_id")
    private int authorID;

    @Column(name = "book_description", length = 10000000)
    private String bookDescription;

    @Column(name = "year_of_release")
    private int yearOfRelease;

    public Book(Integer id, String bookName, int authorID, String bookDescription, int yearOfRelease) {
        this.id = id;
        this.bookName = bookName;
        this.authorID = authorID;
        this.bookDescription = bookDescription;
        this.yearOfRelease = yearOfRelease;
    }

    public Book(){}

    public int getId() {
        return id;
    }

    public void setId(Integer id){
        if(id != null){
            this.id = id;
        }
    }

    public String getBookName() {
        return bookName;
    }

    public int getAuthorID() {
        return authorID;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setId(int id) {
        if(id != 0){
            this.id = id;
        }
    }

    public void setBookName(String bookName) {
        if(bookName != null){
            this.bookName = bookName;
        }
    }

    public void setAuthorID(int authorID) {
        if(authorID != 0){
            this.authorID = authorID;
        }
    }

    public void setBookDescription(String bookDescription) {
        if(bookDescription != null){
            this.bookDescription = bookDescription;
        }
    }

    public void setYearOfRelease(int yearOfRelease) {
        if(yearOfRelease != 0){
            this.yearOfRelease = yearOfRelease;
        }
    }
}
