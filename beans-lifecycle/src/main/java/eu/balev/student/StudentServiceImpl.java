package eu.balev.student;

import eu.balev.student.model.Student;
import eu.balev.student.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
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
    @PostConstruct
    public void init() {

        int totalStudents = 0;

        for (StudentRepository studentRepository : studentRepositories) {
           int studentCnt = studentRepository.count();
           totalStudents+=studentCnt;
            System.out.println("We have " + studentCnt + " students in " + studentRepository.getName() + " repository.");
        }

        System.out.println("We have totally " + totalStudents + " student(s).");
    }
}