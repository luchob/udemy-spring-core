package eu.balev.student.repository;

import eu.balev.student.model.Student;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository("inMemoryRepo")
public class InMemoryStudentRepository implements StudentRepository, InitializingBean, DisposableBean {
    private final List<Student> students = List.of(
            new Student("Nina Bojinova", LocalDate.of(1977, 12, 9)),
            new Student("Lachezar Balev", LocalDate.of(1979, 3, 7)),
            new Student("Lachezar Balev Fake", LocalDate.of(1979, 3, 7))
    );

    public InMemoryStudentRepository() {
        System.out.println("InMemoryStudentRepository is created.");
    }

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public int count() {
        return getAllStudents().size();
    }

    @Override
    public void destroy() {
        System.out.println("InMemoryStudentRepository is destroyed.");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("InMemoryStudentRepository is initialized with " + count() + " students.");
    }
}
