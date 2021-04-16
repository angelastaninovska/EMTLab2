package mk.ukim.finki.demo.web.Rest;

import mk.ukim.finki.demo.Model.Author;
import mk.ukim.finki.demo.Model.Book;
import mk.ukim.finki.demo.Model.Country;
import mk.ukim.finki.demo.Model.dto.BookDto;
import mk.ukim.finki.demo.Service.AuthorService;
import mk.ukim.finki.demo.Service.BookService;
import mk.ukim.finki.demo.Service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public BookRestController(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping
    private List<Book> findAll() {
        return this.bookService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/authors")
    private List<Author> findAllAuthors() {
        return this.authorService.findAll();
    }

    @GetMapping("/countries")
    private List<Country> findAllCountries() {
        return this.countryService.findAll();
    }



    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto book) {
        return this.bookService.save(book)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return this.bookService.edit(id, bookDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.deleteById(id);
        if(this.bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/decrement/{id}")
    public ResponseEntity incrementBookCopies(@PathVariable Long id)
    {
        this.bookService.decrementAvailableCopies(id);
        return ResponseEntity.ok().build();
    }

}
