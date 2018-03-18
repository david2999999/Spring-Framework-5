package guru.springframework.spring5webapp.controller;

import guru.springframework.spring5webapp.respositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    // create a field with CRUD functionality
    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")
    public String getBooks(Model model){
        // creates an attribute books
        // this attribute will contain the list of books
        model.addAttribute("books", bookRepository.findAll());

        return "books";
    }
}
