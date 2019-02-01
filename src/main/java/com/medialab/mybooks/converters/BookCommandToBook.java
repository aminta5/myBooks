package com.medialab.mybooks.converters;

import com.medialab.mybooks.commands.BookCommand;
import com.medialab.mybooks.model.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookCommandToBook implements Converter<BookCommand, Book> {
    private final AuthorCommandToAuthor authorCommandToAuthor;

    //constructor

    public BookCommandToBook(AuthorCommandToAuthor authorCommandToAuthor) {
        this.authorCommandToAuthor = authorCommandToAuthor;
    }

    @Override
    public Book convert(BookCommand source) {



        if(source == null){
            return null;
        }

        final Book book = new Book();


        /*if(source.getAuthorId() != null){
            Author author = new Author();
            author.setId(source.getId());
            book.setAuthor(author);
            author.addBook(book);
        }*/
        book.setAuthor(authorCommandToAuthor.convert(source.getAuthorCommand()));
        book.setId(source.getId());
        book.setTitle(source.getTitle());
        book.setIsbn(source.getIsbn());

        return book;

    }
}
