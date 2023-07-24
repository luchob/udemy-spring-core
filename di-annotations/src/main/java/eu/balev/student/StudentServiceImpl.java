package eu.balev.student;

import eu.balev.student.model.Student;
import eu.balev.student.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(List<StudentRepository> studentRepositories) {
        this.studentRepository = new CompositeRepository(studentRepositories);
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
        System.out.println("We have " + studentRepository.count() + " student(s).");
    }

    static class CompositeRepository implements StudentRepository
    {
        private final List<StudentRepository> studentRepositories;

        public CompositeRepository(List<StudentRepository> studentRepositories) {
            this.studentRepositories = studentRepositories;
        }

        @Override
        public List<Student> getAllStudents() {
            return studentRepositories
                    .stream()
                    .flatMap(r -> r.getAllStudents().stream())
                    .toList();
        }

        @Override
        public int count() {
            return studentRepositories.stream().mapToInt(StudentRepository::count).sum();
        }
    }
}