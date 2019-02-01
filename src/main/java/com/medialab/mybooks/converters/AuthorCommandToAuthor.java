package com.medialab.mybooks.converters;

import com.medialab.mybooks.commands.AuthorCommand;
import com.medialab.mybooks.model.Author;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorCommandToAuthor implements Converter<AuthorCommand, Author> {
    private final BookCommandToBook bookCommandToBook;
    //constructor

    public AuthorCommandToAuthor(BookCommandToBook bookCommandToBook) {
        this.bookCommandToBook = bookCommandToBook;
    }

    @Override
    public Author convert(AuthorCommand source) {
        if(source == null){
            return null;
        }

        final Author author = new Author();
        author.setId(source.getId());
        author.setFirstName(source.getFirstName());
        author.setLastName(source.getLastName());

        if(source.getBooks() != null && source.getBooks().size() > 0){
            source.getBooks().forEach(book -> author.getBooks().add(bookCommandToBook.convert(book)));
        }

        return author;
    }
}
