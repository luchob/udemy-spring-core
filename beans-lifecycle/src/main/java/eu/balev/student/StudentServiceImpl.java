package eu.balev.student;

import eu.balev.student.model.Student;
import eu.balev.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.Lifecycle;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final List<StudentRepository> studentRepositories;

    public StudentServiceImpl(List<StudentRepository> studentRepositories) {
        this.studentRepositories = studentRepositories;
    }

    @Override
    public Set<Student> findYoungestStudents() {
        var sorted =  studentRepositories
                .stream()
                .flatMap(r -> r.getAllStudents().stream())
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
    public void init() {
        System.out.println("We have " + studentRepositories.stream().mapToInt(StudentRepository::count).sum() + " student(s).");
    }
}