package com.medialab.mybooks.services;

import com.medialab.mybooks.commands.AuthorCommand;
import com.medialab.mybooks.model.Author;

import java.util.Set;

public interface AuthorService {
    Set<Author> getAuthors();
    AuthorCommand saveAuthorCommand(AuthorCommand command);
    Author findById(Long id);
    void deleteById(Long id);
}
