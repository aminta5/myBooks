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

    public BookServiceImpl(AuthorRepository authorRepository, BookCommandToBook bookCommandToBook, BookToBookCommand bookToBookCommand, BookRepository bookRepository/*, AuthorCommandToAuthor authorCommandToAuthor*/) {
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

    @Override
    @Transactional
    public BookCommand saveBookCommand(BookCommand command) {
        Optional<Author> authorOptional = authorRepository.findById(command.getAuthorId());

        if(!authorOptional.isPresent()){
            log.error("Author not found for id: " + command.getAuthorId());
            return new BookCommand();
        }

        Author author = authorOptional.get();
        Book book = bookCommandToBook.convert(command);
        book.setAuthor(author);
        author.addBook(book);
        Author savedAuthor = authorRepository.save(author);
        Optional<Book> savedBookOptional = savedAuthor.getBooks().stream().filter(book1 -> book1.getId().equals(command.getId())).findFirst();
        if(!savedBookOptional.isPresent()){
            savedBookOptional = savedAuthor.getBooks().stream().filter(book1 -> book1.getIsbn().equals(command.getIsbn())).findFirst();
        }

        return bookToBookCommand.convert(savedBookOptional.get());

    }


    @Override
    public Book deleteById(Long id) {
        Book bookDel = bookRepository.findById(id).get();
        Author author = authorRepository.findById(bookDel.getAuthor().getId()).get();
        author.getBooks().remove(bookDel);
        authorRepository.save(author);
        bookRepository.delete(bookDel);
        return bookDel;

    }
}
