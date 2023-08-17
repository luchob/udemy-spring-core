package eu.balev.student.repository;

import eu.balev.student.model.Student;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

@Repository("fileRepo")
public class FileStudentRepository implements StudentRepository, BeanNameAware {

    private String name;
    public FileStudentRepository() {
        System.out.println("FileStudentRepository is created.");
    }

    @Override
    public List<Student> getAllStudents() {
        return
                new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("students.csv")))
                        .lines()
                        .map(this::asStudent)
                        .toList();


    }

    private Student asStudent(String s) {
        var line = s.split(",");
        return new Student(line[0].trim(), LocalDate.parse(line[1].trim()));
    }

    @Override
    public int count() {
        return getAllStudents().size();
    }

    @Override
    public String getName() {
        return name;
    }

    @PostConstruct
    void init() {
        System.out.println("FileStudentRepository is initialized with " + count() + " students.");
    }

    @PreDestroy
    void destroy() {
        System.out.println("FileStudentRepository is destroyed.");
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
