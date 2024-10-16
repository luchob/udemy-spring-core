package eu.balev;

import eu.balev.student.StudentService;
import eu.balev.student.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(
    basePackages = "eu.balev"
)
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);

        StudentService studentService = ctx.getBean(StudentService.class);

        System.out.println(studentService.findYoungestStudents());
    }
}