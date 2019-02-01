package com.medialab.mybooks.controllers;

import com.medialab.mybooks.commands.BookCommand;
import com.medialab.mybooks.model.Book;
import com.medialab.mybooks.services.AuthorService;
import com.medialab.mybooks.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    //constructor

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @RequestMapping("/author/{id}/show")
    public String showAuthors(@PathVariable String id, Model model){
        model.addAttribute("books", bookService.getBooks(new Long(id)));
        model.addAttribute("author", authorService.findById(new Long(id)));
        return "book/show";
    }

    @GetMapping
    @RequestMapping("author/{id}/add")
    public String addBook(@PathVariable String id, Model model){
        BookCommand bookCommand = new BookCommand();
        bookCommand.setAuthorCommand(authorService.findCommandById(Long.valueOf(id)));
        model.addAttribute("bookCommand", bookCommand);
        return "book/new";
    }

    @PostMapping("/author/{id}/book")
    public String saveBook(@PathVariable String id, @Valid @ModelAttribute BookCommand bookCommand, Errors errors){

        if(errors.hasErrors()){
            return "book/new";
        }
        //bookCommand.setAuthorId(Long.valueOf(id));
        BookCommand savedBookCommand = bookService.saveBookCommand(bookCommand);

        return "redirect:/author/" + savedBookCommand.getAuthorCommand().getId() + "/show";
    }

    @RequestMapping("book/{id}/delete")
    public String delete(@PathVariable String id){
        Book book = bookService.deleteById(Long.valueOf(id));
        return "redirect:/author/" + book.getAuthor().getId() + "/show";
    }


}
