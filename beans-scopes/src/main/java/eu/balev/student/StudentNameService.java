package eu.balev.student;

import eu.balev.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentNameService {

  private final StudentRepository studentRepository;

  public StudentNameService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public void printNames() {
    this
        .studentRepository
        .getAllStudents()
        .stream()
        .forEach(
            s -> {
              System.out.println("Name: " + s.name() + " Birthday: " + s.birthDay());
            }
        );
  }
}
