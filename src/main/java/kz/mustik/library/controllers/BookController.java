package kz.mustik.library.controllers;

import kz.mustik.library.dao.BookDAO;
import kz.mustik.library.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public String create(@ModelAttribute("book") Book newBook) {
        bookDAO.save(newBook);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int bookId) {
        model.addAttribute("book", bookDAO.show(bookId));
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int bookId) {
        model.addAttribute("book", bookDAO.show(bookId));
        return "/books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book newBook, @PathVariable("id") int bookId) {
        bookDAO.update(newBook, bookId);
        return "redirect:/books";
    }
}
