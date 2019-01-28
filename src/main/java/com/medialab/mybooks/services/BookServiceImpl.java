package com.medialab.mybooks.services;

import com.medialab.mybooks.commands.BookCommand;
import com.medialab.mybooks.converters.BookCommandToBook;
import com.medialab.mybooks.converters.BookToBookCommand;
import com.medialab.mybooks.model.Author;
import com.medialab.mybooks.model.Book;
import com.medialab.mybooks.repositories.AuthorRepository;
import com.medialab.mybooks.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookServiceImpl implements BookService{

    private final AuthorRepository authorRepository;
    private final BookCommandToBook bookCommandToBook;
    private final BookToBookCommand bookToBookCommand;
    private final BookRepository bookRepository;

    //constructor

    public BookServiceImpl(AuthorRepository authorRepository, BookCommandToBook bookCommandToBook, BookToBookCommand bookToBookCommand, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookCommandToBook = bookCommandToBook;
        this.bookToBookCommand = bookToBookCommand;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if(author.isPresent()){
            return author.get().getBooks();
        }
        return null;
    }

    /*@Override
    public void saveBook(BookCommand book, Long id) {
        Author author = authorRepository.findById(id).get();
        author.getBooks().add(bookCommandToBook.convert(book));
    }*/

    @Override
    @Transactional
    public BookCommand saveBookCommand(BookCommand command) {
        System.out.println(command + " " + command.getAuthorId() + " authors ID");
        Optional<Author> authorOptional = authorRepository.findById(command.getAuthorId());
        if(!authorOptional.isPresent()){
            log.error("Author not found for id: " + command.getAuthorId());
        }
        Author author = authorOptional.get();
        Book detachedBook = bookCommandToBook.convert(command);

        author.addBook(detachedBook);
        authorRepository.save(author);
        detachedBook.setAuthor(author);

        Book savedBook = bookRepository.save(detachedBook);
       // Author author = authorRepository.findById(savedBook.getAuthor().getId()).get();
       // author.getBooks().add(savedBook);

        return bookToBookCommand.convert(savedBook);
    }

    @Override
    public Book convert(BookCommand book) {
        return bookCommandToBook.convert(book);
    }

    @Override
    public void deleteById(Long id) {
        Book bookDel = bookRepository.findById(id).get();
        Author author = bookDel.getAuthor();
        author.getBooks().remove(bookDel);
        authorRepository.save(author);
        bookRepository.delete(bookDel);

    }
}
