package book_library;

public class BookDTO {
    private Integer bookId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private int yearOfRelease;

    public BookDTO(Integer bookId, String bookName, String authorName, String bookDescription, int yearOfRelease) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookDescription = bookDescription;
        this.yearOfRelease = yearOfRelease;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId){
        if(bookId != null){
            this.bookId = bookId;
        }
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        if(bookName != null){
            this.bookName = bookName;
        }
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        if(authorName != null){
            this.authorName = authorName;
        }
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        if(bookDescription != null){
            this.bookDescription = bookDescription;
        }
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        if(yearOfRelease != 0){
            this.yearOfRelease = yearOfRelease;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "bookId :" + bookId +
                ", bookName :'" + bookName + '\'' +
                ", authorName :'" + authorName + '\'' +
                ", bookDescription :'" + bookDescription + '\'' +
                ", yearOfRelease :" + yearOfRelease +
                '}';
    }
}
