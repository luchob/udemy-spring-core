package eu.balev.student.repository;

import eu.balev.student.model.Student;
import jakarta.inject.Named;
import java.time.LocalDate;
import java.util.List;

@Named
@MemoryBased
public class InMemoryStudentRepository implements StudentRepository {
    private final List<Student> students = List.of(
            new Student("Nina Bojinova", LocalDate.of(1977, 12, 9)),
            new Student("Lachezar Balev", LocalDate.of(1979, 3, 7)),
            new Student("Lachezar Balev Fake", LocalDate.of(1979, 3, 7))
    );

    @Override
    public List<Student> getAllStudents() {
        return students;
    }


    @Override
    public long count() {
        return getAllStudents().size();
    }
}
