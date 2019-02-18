package com.medialab.mybooks.services;

import com.medialab.mybooks.commands.BookCommand;
import com.medialab.mybooks.converters.BookCommandToBook;
import com.medialab.mybooks.converters.BookToBookCommand;
import com.medialab.mybooks.model.Author;
import com.medialab.mybooks.model.Book;
import com.medialab.mybooks.repositories.AuthorRepository;
import com.medialab.mybooks.repositories.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {
    @Mock
    AuthorRepository authorRepository;
    @Mock
    BookRepository bookRepository;

    private final BookCommandToBook bookCommandToBook;

    private final BookToBookCommand bookToBookCommand;

    BookService service;

    public BookServiceImplTest(){
        bookCommandToBook = new BookCommandToBook();
        bookToBookCommand = new BookToBookCommand();
    }


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new BookServiceImpl(authorRepository, bookCommandToBook, bookToBookCommand, bookRepository);
    }

    @Test
    public void getBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        books.add(new Book());

        Author author = new Author();
        author.setId(1L);
        author.setBooks(books);

        Optional<Author> authorOptional = Optional.of(author);

        when(authorRepository.findById(anyLong())).thenReturn(authorOptional);
        List<Book> returnedBooks = service.getBooks(anyLong());
        assertEquals(2, returnedBooks.size());
        assertNotNull(returnedBooks);
        verify(authorRepository, times(1)).findById(anyLong());
    }

    @Test
    public void saveBookCommand() {
        Optional<Author> authorOptional = Optional.of(new Author());

        Author savedAuthor = new Author();
        savedAuthor.setId(1L);
        savedAuthor.addBook(new Book());
        savedAuthor.getBooks().iterator().next().setId(1L);

        BookCommand bookCommand = new BookCommand();
        bookCommand.setId(2L);
        bookCommand.setAuthorId(savedAuthor.getId());
        Book book = bookCommandToBook.convert(bookCommand);
        book.setAuthor(savedAuthor);
        savedAuthor.addBook(book);

        when(authorRepository.findById(anyLong())).thenReturn(authorOptional);
        when(authorRepository.save(any())).thenReturn(savedAuthor);

        BookCommand returnedCommand = service.saveBookCommand(bookCommand);

        //Author returnedAuthor = authorRepository.findById(returnedCommand.getAuthorId()).get();
        assertNotNull(returnedCommand);
        assertEquals(1, (long)returnedCommand.getAuthorId());
        assertEquals(2, (long)returnedCommand.getId());



    }



    @Test
    public void deleteById() {
    }
}