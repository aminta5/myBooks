package com.medialab.mybooks.commands;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class AuthorCommand {

    private Long id;
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    private List<BookCommand> books = new ArrayList<>();

    //constructor

    public AuthorCommand() {
    }

    //getters and setters

   /* public Long getId() {
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

    public List<BookCommand> getBooks() {
        return books;
    }

    public void setBooks(List<BookCommand> books) {
        this.books = books;
    }*/
    public void addBook(BookCommand book) {
        this.books.add(book);
    }

    @Override
    public String toString() {
        return "AuthorCommand{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + books +
                '}';
    }
}
