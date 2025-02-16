package eu.balev;

import eu.balev.student.StudentService;
import eu.balev.student.model.Student;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
            "eu.balev"
        );

        ctx.registerShutdownHook();

        StudentService studentService = ctx.getBean(StudentService.class);

        Student next = studentService.findYoungestStudents().iterator().next();

        System.out.println(studentService.generateVCard(next));
    }

}