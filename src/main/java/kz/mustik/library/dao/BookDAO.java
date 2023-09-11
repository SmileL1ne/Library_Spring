package kz.mustik.library.dao;

import kz.mustik.library.models.Book;
import kz.mustik.library.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book newBook) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES(?, ?, ?)",
                newBook.getName(), newBook.getAuthor(), newBook.getYear());
    }

    public Book show(int bookId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE bookId=?", new Object[]{bookId},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void update(Book newBook, int bookId) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE bookId=?",
                newBook.getName(), newBook.getAuthor(), newBook.getYear(), bookId);
    }

    public void delete(int bookId) {
        jdbcTemplate.update("DELETE FROM Book WHERE bookId=?", bookId);
    }

    public Optional<Person> getBookOwnerById(int bookId) {
        return jdbcTemplate.query("SELECT Person.* FROM Person JOIN Book ON Book.personId = Person.personId " +
                "WHERE bookId=?", new Object[]{bookId}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }
    public void book(Person onlyIdPerson, int bookId) {
        jdbcTemplate.update("UPDATE Book SET personId=? WHERE bookId=?", onlyIdPerson.getPersonId(), bookId);
    }

    public void releaseBook(int personId) {
        jdbcTemplate.update("UPDATE Book SET personId=NULL WHERE personId=?", personId);
    }
}
