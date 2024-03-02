package book_library;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/author/get/{id}")
    public Author findAuthorById(@PathVariable Integer id){
        return authorService.findAuthor(id);
    }

    @GetMapping(value = "/author/getname/{id}")
    public String getAuthorName(@PathVariable Integer id){
        return authorService.getAuthorName(id);
    }

    @GetMapping(value = "author/getbyname/{name}")
    public Author getAuthorByName(@PathVariable String name){
        return authorService.findAuthorByName(name);
    }

    @GetMapping(value =  "/author/getall")
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @PostMapping(value = "/author/add")
    public Author addAuthor(@RequestBody Author author){
        return authorService.addAuthor(author);
    }

    @PutMapping(value = "/author/update/{id}")
    public Author updateAuthor(@PathVariable Integer id, @RequestBody Author author){
        return authorService.updateAuthor(id,author);
    }

    @DeleteMapping(value = "/author/delete/{id}")
    public void deleteAuthor(@PathVariable Integer id){
        authorService.deleteAuthor(id);
    }

//    @GetMapping(value = "/author/getbookswrittenbyauthor/{authorName}")
//    public List<BookDTO> booksByAuthor(@PathVariable String authorName){
//        return authorService.getBooksWrittenByAuthor(authorName);
//    }

    @GetMapping(value = "/author/getbooksbyauthor/{authorName}")
    public List<BookDTO> booksByAuthor(@PathVariable String authorName){
        return authorService.getBooksByAuthor(authorName);
    }
}
