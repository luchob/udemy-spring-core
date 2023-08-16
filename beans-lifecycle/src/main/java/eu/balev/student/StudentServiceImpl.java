package eu.balev.student;

import eu.balev.student.model.Student;
import eu.balev.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.Lifecycle;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService, SmartLifecycle {

    private boolean running = false;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(@Qualifier("inMemoryRepo") StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
    public void init() {
        System.out.println("We have " + studentRepository.count() + " student(s).");
    }

    @Override
    public void start() {
        running = true;
        System.out.println("Starting the service...");
    }

    @Override
    public void stop() {
        running = false;
        System.out.println("Stopping the service...");
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}