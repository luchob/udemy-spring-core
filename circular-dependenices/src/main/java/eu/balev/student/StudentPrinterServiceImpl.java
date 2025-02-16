package eu.balev.student;

import eu.balev.student.model.Student;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class StudentPrinterServiceImpl implements StudentPrinterService{

  private final StudentService studentService;

  public StudentPrinterServiceImpl(@Lazy StudentService studentService) {
    this.studentService = studentService;
  }

  @Override
  public void printStudent(Student student) {
    System.out.println("---------" + student.name() + "----------");
    System.out.println(studentService.generateVCard(student));
    System.out.println("----------------------------");

  }
}
