package eu.balev.student.repository;

import eu.balev.student.model.Student;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository("inMemoryRepo")
public class InMemoryStudentRepository implements StudentRepository {
    private final List<Student> students = List.of(
            new Student("Nina Bojinova", LocalDate.of(1977, 12, 9)),
            new Student("Lachezar Balev", LocalDate.of(1979, 3, 7)),
            new Student("Lachezar Balev Fake", LocalDate.of(1979, 3, 7))
    );

    public InMemoryStudentRepository() {
        System.out.println("InMemoryStudentRepository instantiated");
    }

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public int count() {
        return getAllStudents().size();
    }


    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("In memory repo manages " + count() + "students. Cleanup complete. Bye!");
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        System.out.println("In memory repo manages " + count() + "students. Init complete. Hello!");
    }
}
