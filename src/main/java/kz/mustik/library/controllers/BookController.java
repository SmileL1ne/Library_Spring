package kz.mustik.library.controllers;

import kz.mustik.library.dao.BookDAO;
import kz.mustik.library.dao.PersonDAO;
import kz.mustik.library.models.Book;
import kz.mustik.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController (BookDAO bookDAO, PersonDAO personDao) {
        this.bookDAO = bookDAO;
        this.personDAO = personDao;
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
    public String show(Model model, @PathVariable("id") int bookId, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(bookId));
        Optional<Person> owner = bookDAO.getBookOwnerById(bookId);
        if (owner.isPresent()) {
            model.addAttribute("owner", owner.get());
        } else {
            model.addAttribute("people", personDAO.index());
        }
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

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int bookId) {
        bookDAO.delete(bookId);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/reserve")
    public String reserve(@ModelAttribute("person")Person onlyIdPerson, @PathVariable("id") int bookId) {
        bookDAO.book(onlyIdPerson, bookId);
        return "redirect:/books" + bookId;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int bookId) {
        bookDAO.releaseBook(bookId);
        return "redirect:/books" + bookId;
    }

}
