package mk.ukim.finki.demo.Service;

import mk.ukim.finki.demo.Model.Author;
import mk.ukim.finki.demo.Model.Book;
import mk.ukim.finki.demo.Model.Enumerations.Categories;
import mk.ukim.finki.demo.Model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> save(BookDto book);
    Optional<Book> edit(Long id,BookDto book);
    Optional<Book> editWithParams(Long id, String name, Categories category, Long author, Integer availableCopies);
    void deleteById(Long id);
    void decrementAvailableCopies(Long id);
}
