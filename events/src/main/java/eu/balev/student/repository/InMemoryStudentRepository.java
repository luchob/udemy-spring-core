package eu.balev.student.repository;

import eu.balev.student.model.Student;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Priority;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class InMemoryStudentRepository implements StudentRepository {

    private final List<Student> students = new LinkedList<>(List.of(
            new Student("Nina Bojinova", LocalDate.of(1977, 12, 9)),
            new Student("Lachezar Balev", LocalDate.of(1979, 3, 7)),
            new Student("Lachezar Balev Fake", LocalDate.of(1979, 3, 7))
    ));

    @Override
    public List<Student> getAllStudents() {
        return students;
    }


    @Override
    public long count() {
        return students.size();
    }

    @Override
    public void createStudent(Student student) {
        students.add(student);
    }
}
