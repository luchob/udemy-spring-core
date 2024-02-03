package eu.balev.student;

import eu.balev.student.config.FileBased;
import eu.balev.student.config.MemoryBased;
import eu.balev.student.model.Student;
import eu.balev.student.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Provider;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final Provider<StudentRepository> studentRepositoryProvider;
    private final String initMessage;

    @Inject
    public StudentServiceImpl(
        @FileBased Provider<StudentRepository> studentRepositoryProvider,
        @Value("${init.message}") String initMessage) {
        this.studentRepositoryProvider = studentRepositoryProvider;
        this.initMessage = initMessage;
    }

    @Override
    public Set<Student> findYoungestStudents() {
        var sorted =  studentRepositoryProvider.get()
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
        System.out.printf((initMessage) + "%n", studentRepositoryProvider.get().count());
    }

    @Override
    @PreDestroy
    public void destroy() {
        // Put cleanup logic here.
        System.out.println("The application shuts down...");
    }
}
