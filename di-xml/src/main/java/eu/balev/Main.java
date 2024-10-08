package eu.balev;

import eu.balev.student.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("beans.xml");

        StudentService studentService = applicationContext.getBean("studentService", StudentService.class);

        System.out.println(studentService.findYoungestStudents());
    }
}