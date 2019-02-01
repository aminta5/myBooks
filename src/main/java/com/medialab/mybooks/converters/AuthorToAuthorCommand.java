package com.medialab.mybooks.converters;

import com.medialab.mybooks.commands.AuthorCommand;
import com.medialab.mybooks.model.Author;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorToAuthorCommand implements Converter<Author, AuthorCommand> {
    private final BookToBookCommand bookToBookCommand;
    //constructor


    public AuthorToAuthorCommand(BookToBookCommand bookToBookCommand) {
        this.bookToBookCommand = bookToBookCommand;
    }

    @Override
    public AuthorCommand convert(Author source) {
        if(source == null){
            return null;
        }

        final AuthorCommand authorCommand = new AuthorCommand();
        authorCommand.setId(source.getId());
        authorCommand.setFirstName(source.getFirstName());
        authorCommand.setLastName(source.getLastName());

        if(source.getBooks() != null && source.getBooks().size() > 0){
            source.getBooks().forEach(book -> authorCommand.getBooks().add(bookToBookCommand.convert(book)));
        }

        return authorCommand;
    }
}
