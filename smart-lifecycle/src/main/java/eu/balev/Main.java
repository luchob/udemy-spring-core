package eu.balev;

import eu.balev.student.StudentService;
import eu.balev.student.StudentServiceImpl;
import eu.balev.student.model.Student;
import eu.balev.student.repository.FileStudentRepository;
import eu.balev.student.repository.InMemoryStudentRepository;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
            "eu.balev"
        );
        System.out.println("1----");
        ctx.registerShutdownHook();
        System.out.println("2----");
        ctx.start();
        System.out.println("3----");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter to stop...");
        scanner.nextLine();
        System.out.println("4----");

        ctx.stop();
        System.out.println("5----");

        System.out.println("Press Enter to start...");
        scanner.nextLine();
        ctx.start();
        System.out.println("6----");

        System.out.println("Press Enter to shutdown...");
        scanner.nextLine();
    }

}