package eu.balev;

import eu.balev.student.repository.StudentRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext("eu.balev");

        ctx.registerShutdownHook();

        var studentRepo = ctx.getBean("fileRepo", StudentRepository.class);
        studentRepo.getAllStudents();
    }
}