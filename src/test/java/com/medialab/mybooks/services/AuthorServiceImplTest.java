package com.medialab.mybooks.services;

import com.medialab.mybooks.commands.AuthorCommand;
import com.medialab.mybooks.converters.AuthorCommandToAuthor;
import com.medialab.mybooks.converters.AuthorToAuthorCommand;
import com.medialab.mybooks.converters.BookCommandToBook;
import com.medialab.mybooks.converters.BookToBookCommand;
import com.medialab.mybooks.model.Author;
import com.medialab.mybooks.repositories.AuthorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class AuthorServiceImplTest {
    @Mock
    AuthorRepository authorRepository;

    private final AuthorToAuthorCommand authorToAuthorCommand;

    private final AuthorCommandToAuthor authorCommandToAuthor;

    AuthorService service;

    public AuthorServiceImplTest(){
        authorCommandToAuthor = new AuthorCommandToAuthor(new BookCommandToBook());
        authorToAuthorCommand = new AuthorToAuthorCommand(new BookToBookCommand());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new AuthorServiceImpl(authorRepository, authorToAuthorCommand, authorCommandToAuthor);
    }

    @Test
    public void getAuthors() {
        //given
        Set<Author> authors = new HashSet<>();
        Author author1 = new Author();
        Author author2 = new Author();
        author1.setId(1L);
        author2.setId(2L);
        authors.add(author1);
        authors.add(author2);

        when(authorRepository.findAll()).thenReturn(authors);

        Set<Author> returnedAuthors = service.getAuthors();
        assertEquals(authors.size(), returnedAuthors.size());
        verify(authorRepository, times(1)).findAll();

    }

    @Test
    public void findById() {
        Author author = new Author();
        author.setId(1L);
        Optional<Author> authorOptional = Optional.of(author);

        when(authorRepository.findById(anyLong())).thenReturn(authorOptional);

        Author returnedAuthor = service.findById(anyLong());
        assertNotNull(returnedAuthor);
        assertEquals(1, (long)returnedAuthor.getId());
        verify(authorRepository, times(1)).findById(anyLong());
    }

    @Test
    public void saveAuthorCommand() {
        AuthorCommand authorCommand = new AuthorCommand();
        authorCommand.setId(1L);


        when(authorRepository.save(any())).thenReturn(authorCommandToAuthor.convert(authorCommand));

        AuthorCommand savedCommand = service.saveAuthorCommand(authorCommand);
        assertNotNull(savedCommand);
        assertEquals(authorCommand.getId(), savedCommand.getId());
    }

    @Test
    public void deleteById() {
        Long idToDelete = Long.valueOf(2L);
        authorRepository.deleteById(idToDelete);

        verify(authorRepository, times(1)).deleteById(anyLong());
    }
}