package eu.balev.student.repository;

import eu.balev.student.config.FileBased;
import eu.balev.student.model.Student;
import jakarta.inject.Named;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

@Named
@FileBased
public class FileStudentRepository implements StudentRepository {
    @Override
    public List<Student> getAllStudents() {
        return
                new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("students.csv")))
                        .lines()
                        .map(this::asStudent)
                        .toList();


    }

    @Override
    public long count() {
        return getAllStudents().size();
    }

    private Student asStudent(String s) {
        var line = s.split(",");
        return new Student(line[0].trim(), LocalDate.parse(line[1].trim()));
    }
}
