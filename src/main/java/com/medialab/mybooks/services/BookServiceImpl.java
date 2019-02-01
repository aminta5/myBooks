package com.medialab.mybooks.services;

import com.medialab.mybooks.commands.BookCommand;
import com.medialab.mybooks.converters.AuthorCommandToAuthor;
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
    private final AuthorCommandToAuthor authorCommandToAuthor;

    //constructor

    public BookServiceImpl(AuthorRepository authorRepository, BookCommandToBook bookCommandToBook, BookToBookCommand bookToBookCommand, BookRepository bookRepository, AuthorCommandToAuthor authorCommandToAuthor) {
        this.authorRepository = authorRepository;
        this.bookCommandToBook = bookCommandToBook;
        this.bookToBookCommand = bookToBookCommand;
        this.bookRepository = bookRepository;
        this.authorCommandToAuthor = authorCommandToAuthor;
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
        Author author = authorCommandToAuthor.convert(command.getAuthorCommand());
        author.addBook(bookCommandToBook.convert(command));
        Author savedAuthor = authorRepository.save(author);
        return bookToBookCommand.convert(savedAuthor.getBooks().stream().filter(book -> book.getId().equals(command.getId())).findFirst().get());




        /*Author author = command.author;

        System.out.println(command + " " + command.getAuthorId() + " authors ID");
        Optional<Author> authorOptional = authorRepository.findById(command.getAuthorId());
        if(!authorOptional.isPresent()){
            log.error("Author not found for id: " + command.getAuthorId());
        }
        Author author = authorOptional.get();
        System.out.println(author.getBooks());
        Book detachedBook = bookCommandToBook.convert(command);
        System.out.println(detachedBook);

        author.addBook(detachedBook);
        detachedBook.setAuthor(author);

        Book savedBook = bookRepository.save(detachedBook);*/

        //return bookToBookCommand.convert(savedBook);
    }

    @Override
    public Book convert(BookCommand book) {
        return bookCommandToBook.convert(book);
    }

    @Override
    public Book deleteById(Long id) {
        Book bookDel = bookRepository.findById(id).get();
        Author author = bookDel.getAuthor();
        author.getBooks().remove(bookDel);
        authorRepository.save(author);
        bookRepository.delete(bookDel);
        return bookDel;

    }
}
