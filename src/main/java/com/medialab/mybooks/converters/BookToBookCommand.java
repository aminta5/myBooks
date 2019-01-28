package com.medialab.mybooks.converters;

import com.medialab.mybooks.commands.BookCommand;
import com.medialab.mybooks.model.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookToBookCommand implements Converter<Book, BookCommand> {
    //constructor

    public BookToBookCommand() {
    }

    @Override
    public BookCommand convert(Book source) {
        if(source == null){
            return null;
        }

        final BookCommand bookCommand = new BookCommand();

        bookCommand.setId(source.getId());
        bookCommand.setTitle(source.getTitle());
        bookCommand.setIsbn(source.getIsbn());

        return bookCommand;
    }
}
