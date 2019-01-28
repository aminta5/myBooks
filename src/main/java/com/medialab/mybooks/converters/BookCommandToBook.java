package com.medialab.mybooks.converters;

import com.medialab.mybooks.commands.BookCommand;
import com.medialab.mybooks.model.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookCommandToBook implements Converter<BookCommand, Book> {

    //constructor

    public BookCommandToBook() {
    }

    @Override
    public Book convert(BookCommand source) {
        if(source == null){
            return null;
        }

        final Book book = new Book();
        book.setId(source.getId());
        book.setTitle(source.getTitle());
        book.setIsbn(source.getIsbn());

        return book;

    }
}
