package com.medialab.mybooks.commands;

import com.medialab.mybooks.model.Book;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class AuthorCommand {

    private Long id;
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;
    private List<Book> books = new ArrayList<>();

    //constructor

    public AuthorCommand() {
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public void addBook(Book book) {
        this.books.add(book);
    }
}
