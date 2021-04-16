package mk.ukim.finki.demo.Service.Impl;

import mk.ukim.finki.demo.Model.Author;
import mk.ukim.finki.demo.Model.Book;
import mk.ukim.finki.demo.Model.Enumerations.Categories;
import mk.ukim.finki.demo.Model.dto.BookDto;
import mk.ukim.finki.demo.Repository.AuthorRepository;
import mk.ukim.finki.demo.Repository.BookRepository;
import mk.ukim.finki.demo.Service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }



    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(BookDto book) {
        Author au = this.authorRepository.findById(book.getAuthor()).get();
        Book bookToSave = new Book(book.getName(),book.getCategory(),au,book.getAvailableCopies());
        return Optional.of(this.bookRepository.save(bookToSave));
    }

    @Override
    public Optional<Book> editWithParams(Long id, String name, Categories category, Long author, Integer availableCopies) {
        Author au = this.authorRepository.findById(author).get();
        Book book = this.bookRepository.findById(id).get();
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(au);
        book.setAvailableCopies(availableCopies);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id,BookDto book) {
        Author au = this.authorRepository.findById(book.getAuthor()).get();
        Book bookToEdit = this.bookRepository.findById(id).get();
        bookToEdit.setName(book.getName());
        bookToEdit.setCategory(book.getCategory());
        bookToEdit.setAuthor(au);
        bookToEdit.setAvailableCopies(book.getAvailableCopies());

        return Optional.of(this.bookRepository.save(bookToEdit));
    }
    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public void decrementAvailableCopies(Long id) {
        Book book = this.bookRepository.findById(id).get();
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        this.bookRepository.save(book);
    }


}
