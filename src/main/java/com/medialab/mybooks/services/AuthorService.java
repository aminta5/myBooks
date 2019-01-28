package com.medialab.mybooks.services;

import com.medialab.mybooks.commands.AuthorCommand;
import com.medialab.mybooks.model.Author;
import com.medialab.mybooks.model.Book;

import java.util.List;
import java.util.Set;

public interface AuthorService {
    Set<Author> getAuthors();
    AuthorCommand saveAuthorCommand(AuthorCommand command);
    Author findById(Long id);
    List<Book> getBooks(Long id);
    AuthorCommand convert(Author author);
    AuthorCommand findCommandById(Long id);
    void deleteById(Long id);
}
