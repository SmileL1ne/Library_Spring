package kz.mustik.library.dao;

import kz.mustik.library.models.Book;
import kz.mustik.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, surname, dateOfBirth, pName) VALUES (?, ?, ?, ?)",
                person.getName(), person.getSurname(), person.getDateOfBirth(), person.getPName());
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE personId=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, surname=?, dateOfBirth=?, pName=? WHERE personId=?",
                updatedPerson.getName(), updatedPerson.getSurname(), updatedPerson.getDateOfBirth(), updatedPerson.getPName(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE personId=?", id);
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE personId=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
