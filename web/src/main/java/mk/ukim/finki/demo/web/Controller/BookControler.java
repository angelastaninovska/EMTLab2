package mk.ukim.finki.demo.web.Controller;

import mk.ukim.finki.demo.Model.Author;
import mk.ukim.finki.demo.Model.Book;
import mk.ukim.finki.demo.Model.Enumerations.Categories;
import mk.ukim.finki.demo.Service.AuthorService;
import mk.ukim.finki.demo.Service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookControler {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookControler(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    String loadAllBooks(Model model)
    {
        List<Book> bookList = this.bookService.findAll();
        model.addAttribute("bookList",bookList);
        return "showBooks";
    }

    @PostMapping("/delete/{id}")
    String deleteBook(@PathVariable Long id)
    {
        this.bookService.deleteById(id);
        return "showBooks";
    }

    @GetMapping("/edit-form/{id}")
    String editBook(@PathVariable Long id, Model model)
    {
        Book book = this.bookService.findById(id).get();
        List<Author> authorList = this.authorService.findAll();
        model.addAttribute("book",book);
        model.addAttribute("authors",authorList);
        return "Edit";
    }

    @PostMapping("/edit")
    String postEdit(         @RequestParam(required = false) Long id,
                             @RequestParam String name,
                             @RequestParam Integer quantity,
                             @RequestParam Categories category,
                             @RequestParam Long author)
    {
        Book book = this.bookService.findById(id).get();
        this.bookService.editWithParams(id,name,category,author,quantity);
        return "redirect:/books";
    }


}
