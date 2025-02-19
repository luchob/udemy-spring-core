package eu.balev;

import eu.balev.student.StudentService;
import eu.balev.student.model.Student;
import java.time.LocalDate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
            "eu.balev"
        );

        ctx.registerShutdownHook();

        StudentService studentService = ctx.getBean(StudentService.class);

        Student newStudent = new Student("John Smith", LocalDate.of(1999, 1, 1));
        studentService.createStudent(newStudent);
    }

}