package eu.balev.student.model;

import java.time.LocalDate;
import java.util.Objects;

public record Student(String name, LocalDate birthDay) {

    public Student(String name, LocalDate birthDay) {
        this.name = Objects.requireNonNull(name);
        this.birthDay = Objects.requireNonNull(birthDay);
    }

    public String firstName() {
        String[] names = this.name.split(" ");
        if (names.length >= 1) {
            return names[0];
        }
        return name;
    }

    public String lastName() {
        String[] names = this.name.split(" ");
        if (names.length >= 1) {
            return names[names.length-1];
        }
        return name;
    }
}
