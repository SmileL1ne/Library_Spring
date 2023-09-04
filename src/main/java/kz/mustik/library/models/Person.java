package kz.mustik.library.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private int personId;

    @NotEmpty(message = "Write your name, please!")
    @Size(min = 2, max = 30, message = "Name length should be between 2 and 30 characters!")
    private String name;

    @NotEmpty(message = "Write your surname, please!")
    @Size(min = 2, max = 30, message = "Name length should be between 2 and 30 characters!")
    private String surname;

    @NotEmpty(message = "Write your date of birth in YYYY-MM-DD format, please!")
    @Size(min = 10, max = 10, message = "Write your date of birth in YYYY-MM-DD format, please!")
    private String dateOfBirth;

}
