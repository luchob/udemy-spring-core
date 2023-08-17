package eu.balev;

import eu.balev.student.StudentService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext("eu.balev");

        ctx.registerShutdownHook();

        System.out.println("Bye!");
    }
}