package com.medialab.mybooks.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Book {
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @ManyToOne
    @JoinColumn(name="authorId")
    private Author author;


    //constructors


    public Book() {
    }

    public Book(@NotBlank(message = "Title is required") String title, @NotBlank(message = "ISBN is required") String isbn, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }

    //constructor for bootstrap class initialization
    public Book(@NotBlank(message = "Title is required") String title, @NotBlank(message = "ISBN is required") String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    //getters and setters


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id) &&
                title.equals(book.title) &&
                isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isbn);
    }
}
