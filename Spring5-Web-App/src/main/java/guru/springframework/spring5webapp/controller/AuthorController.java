package guru.springframework.spring5webapp.controller;

import guru.springframework.spring5webapp.respositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorController {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/authors")
    public String showAuthors(Model model){
        // add the authors into the attribute
        model.addAttribute("authors", authorRepository.findAll());

        return "authors";
    }











}
