package eu.balev;


import eu.balev.student.StudentNameService;
import eu.balev.student.StudentService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
            "eu.balev"
        );

        ctx.registerShutdownHook();

        StudentService studentService = ctx.getBean(StudentService.class);
        StudentNameService studentNameService = ctx.getBean(StudentNameService.class);

        System.out.println(studentService.findYoungestStudents());
        studentNameService.printNames();
    }
}