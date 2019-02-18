package com.medialab.mybooks.controllers;

import com.medialab.mybooks.services.AuthorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AuthorControllerTest {
    @Mock
    AuthorService authorService;
    AuthorController authorController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        authorController = new AuthorController(authorService);
    }

    @Test
    public void getAuthors() {
    }

    @Test
    public void newAuthor() {
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }
}