package kz.mustik.library.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private int bookId;

    @NotEmpty(message = "Write name of the book, please!")
    private String name;
    @NotEmpty(message = "Write author's name, please!")
    private String author;
    @NotEmpty(message = "Write book's year, please!")
    @Min(value = 0, message = "Year should be positive!")
    private int year;
}
