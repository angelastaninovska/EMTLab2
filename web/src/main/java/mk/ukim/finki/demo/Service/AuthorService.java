package mk.ukim.finki.demo.Service;

import mk.ukim.finki.demo.Model.Author;
import mk.ukim.finki.demo.Model.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
}
