package eu.balev;

import eu.balev.student.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext("eu.balev");

        StudentService studentService = ctx.getBean(StudentService.class);

        System.out.println(studentService.findYoungestStudents());
    }
}