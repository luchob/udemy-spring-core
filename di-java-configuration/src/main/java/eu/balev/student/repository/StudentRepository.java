package eu.balev.student.repository;

import eu.balev.student.model.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> getAllStudents();

    int count();
}
