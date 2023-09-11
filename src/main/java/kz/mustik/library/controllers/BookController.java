package kz.mustik.library.controllers;

import kz.mustik.library.dao.BookDAO;
import kz.mustik.library.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;

    @Autowired
    public BookController (BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/newBook")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/newBook";
    }
}
