package eu.balev.student.repository;

import eu.balev.student.model.Student;
import jakarta.annotation.PreDestroy;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Primary
@Lazy
public class InMemoryStudentRepository implements StudentRepository {

    private int count = 0;

    private final List<Student> students = List.of(
            new Student("Nina Bojinova", LocalDate.of(1977, 12, 9)),
            new Student("Lachezar Balev", LocalDate.of(1979, 3, 7)),
            new Student("Lachezar Balev Fake", LocalDate.of(1979, 3, 7))
    );

    @Override
    public List<Student> getAllStudents() {
        count++;
        System.out.println("The repo " + this + " was called " + count + " times.");
        return students;
    }


    @Override
    public long count() {
        count++;
        System.out.println("The repo " + this + " was called " + count + " times.");
        return students.size();
    }
}
