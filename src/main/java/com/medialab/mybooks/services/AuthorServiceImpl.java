package com.medialab.mybooks.services;

import com.medialab.mybooks.commands.AuthorCommand;
import com.medialab.mybooks.converters.AuthorCommandToAuthor;
import com.medialab.mybooks.converters.AuthorToAuthorCommand;
import com.medialab.mybooks.model.Author;
import com.medialab.mybooks.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;
    private final AuthorToAuthorCommand authorToAuthorCommand;
    private final AuthorCommandToAuthor authorCommandToAuthor;

    //constructor

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorToAuthorCommand authorToAuthorCommand, AuthorCommandToAuthor authorCommandToAuthor) {
        this.authorRepository = authorRepository;
        this.authorToAuthorCommand = authorToAuthorCommand;
        this.authorCommandToAuthor = authorCommandToAuthor;
    }

    @Override
    public Set<Author> getAuthors() {
        Set<Author> authors = new HashSet<>();
        authorRepository.findAll().forEach(authors :: add);
        return authors;
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public AuthorCommand saveAuthorCommand(AuthorCommand command) {
        Author detachedAuthor = authorCommandToAuthor.convert(command);
        Author savedAuthor = authorRepository.save(detachedAuthor);
        return authorToAuthorCommand.convert(savedAuthor);
    }

    @Override
    public void deleteById(Long id) {
        Author author = authorRepository.findById(id).get();
        authorRepository.delete(author);
    }
}
