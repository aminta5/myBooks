package com.medialab.mybooks.controllers;

import com.medialab.mybooks.commands.AuthorCommand;
import com.medialab.mybooks.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class AuthorController {
    private final AuthorService authorService;


    //constructor
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping({"", "/", "/author/authors"})
    public String getAuthors(Model model){
        model.addAttribute("authors", authorService.getAuthors());
        return "author/authors";
    }






    @RequestMapping("/author/new")
    public String newAuthor(Model model){
        model.addAttribute("authorCommand", new AuthorCommand());
        return "author/new";
    }

    @PostMapping("/author")
    public String save(@ModelAttribute @Valid AuthorCommand authorCommand, Errors errors){
        if(errors.hasErrors()){
            return "author/new";
        }
        AuthorCommand savedCommand = authorService.saveAuthorCommand(authorCommand);
        return "redirect:/author/authors" /*+ savedCommand.getId()*/;
    }

    @RequestMapping("author/{id}/delete")
    public String delete(@PathVariable String id){
        authorService.deleteById(Long.valueOf(id));
        return "redirect:/author/authors";
    }



}
