package eu.balev.student;

import eu.balev.student.model.Student;
import eu.balev.student.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final String message;

    public StudentServiceImpl(List<StudentRepository> studentRepositories,
        @Value("${init.message}") String message) {
        this.studentRepository = new CompositeStudentRepository(studentRepositories);
        this.message = message;
    }

    static class CompositeStudentRepository implements StudentRepository {

        private final List<StudentRepository> studentRepositories;

        public CompositeStudentRepository(List<StudentRepository> studentRepositories) {
            this.studentRepositories = studentRepositories;
        }

        @Override
        public List<Student> getAllStudents() {
            return studentRepositories
                .stream()
                .flatMap(sr -> sr.getAllStudents().stream())
                .collect(Collectors.toList());
        }

        @Override
        public long count() {
            return studentRepositories.stream().mapToLong(StudentRepository::count).sum();
        }
    }

    @Override
    public Set<Student> findYoungestStudents() {
        var sorted =  studentRepository
                .getAllStudents()
                .stream()
                .sorted(Comparator.comparing(Student::birthDay).reversed())
                .toList();

        if (sorted.isEmpty()) {
            return Set.of();
        } else {
            LocalDate lastBirthDay = sorted.get(0).birthDay();

            return sorted
                    .stream()
                    .filter(s -> s.birthDay().equals(lastBirthDay))
                    .collect(Collectors.toSet());
        }
    }

    @Override
    @PostConstruct
    public void init() {
        System.out.printf(message, studentRepository.count());
    }
}
