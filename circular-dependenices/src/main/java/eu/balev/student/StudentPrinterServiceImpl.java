package eu.balev.student;

import eu.balev.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentPrinterServiceImpl implements StudentPrinterService{

  private StudentService studentService;

  @Autowired
  public StudentPrinterServiceImpl setStudentService(StudentService studentService) {
    this.studentService = studentService;
    return this;
  }

  @Override
  public void printDetails(Student student) {
    System.out.println("------");
    System.out.println(student.name());
    System.out.println(studentService.generateVCard(student));
  }
}
