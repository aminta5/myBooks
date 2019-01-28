package com.medialab.mybooks.converters;

import com.medialab.mybooks.commands.AuthorCommand;
import com.medialab.mybooks.model.Author;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorToAuthorCommand implements Converter<Author, AuthorCommand> {

    //constructor


    public AuthorToAuthorCommand() {
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

        return authorCommand;
    }
}
