package com.medialab.mybooks.bootstrap;

import com.medialab.mybooks.model.Author;
import com.medialab.mybooks.model.Book;
import com.medialab.mybooks.repositories.AuthorRepository;
import com.medialab.mybooks.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AuthorBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final AuthorRepository authorRepository;

    //constructor


    public AuthorBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        authorRepository.saveAll(getAuthors());
    }

    private Set<Author> getAuthors(){
        Set<Author> authors = new HashSet<>();
        // first author
        Author dan = new Author("Dan", "Brown");
        Book ddc = new Book("DaVinci code", "9971-5-0210-0");
        Book ad = new Book("Angels and Demons;", "85-359-0277-5");
        Book o = new Book("Origin", "0-684-84328-5");
        ddc.setAuthor(dan);
        ad.setAuthor(dan);
        o.setAuthor(dan);

        dan.getBooks().add(ddc);
        dan.getBooks().add(ad);
        dan.getBooks().add(o);


        //second author
        Author agatha = new Author("Agatha", "Christi");
        Book book1 = new Book("And there were none", "1-84356-028-3");
        Book book2 = new Book("Murder on the Orient Express", "0-943396-04-2");
        Book book3 = new Book("The A. B. C. Murders", "0-9752298-0-X");
        book1.setAuthor(agatha);
        book2.setAuthor(agatha);
        book3.setAuthor(agatha);

        agatha.getBooks().add(book1);
        agatha.getBooks().add(book2);
        agatha.getBooks().add(book3);

        //adding the authors in the set
        authors.add(dan);
        authors.add(agatha);

        return authors;
    }
}
