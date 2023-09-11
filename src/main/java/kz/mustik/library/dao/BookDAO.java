package kz.mustik.library.dao;

import kz.mustik.library.models.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
