package com.medialab.mybooks.services;

import com.medialab.mybooks.commands.BookCommand;
import com.medialab.mybooks.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks(Long id);
    BookCommand saveBookCommand(BookCommand command);
    Book convert(BookCommand book);
    Book deleteById(Long id);
}
