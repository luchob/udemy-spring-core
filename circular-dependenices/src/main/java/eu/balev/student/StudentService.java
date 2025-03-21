package eu.balev.student;

import eu.balev.student.model.Student;
import java.util.Set;

public interface StudentService {

    Set<Student> findYoungestStudents();

    void init();

    void destroy();

    String generateVCard(Student s);

    void printAllStudents();
}
