package com.example.book_library.book;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity()
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer id;

    @Column(name = "book_name", nullable = false)
    @NotNull(message = "book name can't be empty")
    private String bookName;

    @Column(name = "author_name", nullable = false)
    @NotNull(message = "author name can't be empty")
    private String authorName;

    @Column(name = "book_description", length = 10000000)
    private String bookDescription;

    @Column(name = "year_of_release")
    private int yearOfRelease;

    @Column(name = "books_count", nullable = false)
    @NotNull(message = "count of books can't be empty")
    private Integer booksCount;

    @Column(name = "section_number", nullable = false)
    @NotNull(message = "section number can't be empty")
    private Integer sectionNumber;

    public Book(Integer id, String bookName, String authorName,
                String bookDescription, int yearOfRelease,
                Integer booksCount, Integer sectionNumber) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookDescription = bookDescription;
        this.yearOfRelease = yearOfRelease;
        this.booksCount = booksCount;
        this.sectionNumber = sectionNumber;
    }

    public Book(){}

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public Integer getBooksCount(){
        return booksCount;
    }

    public Integer getSectionNumber(){
        return sectionNumber;
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

    public void setAuthorName(String authorName) {
        if(authorName != null){
            this.authorName = authorName;
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

    public void setBooksCount(Integer booksCount){
        if(booksCount != null){
            this.booksCount = booksCount;
        }
    }

    public void setSectionNumber(Integer sectionNumber){
        if(sectionNumber != null){
            this.sectionNumber = sectionNumber;
        }
    }
}
