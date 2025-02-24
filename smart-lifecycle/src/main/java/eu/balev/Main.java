package eu.balev;

import eu.balev.student.StudentService;
import eu.balev.student.StudentServiceImpl;
import eu.balev.student.model.Student;
import eu.balev.student.repository.FileStudentRepository;
import eu.balev.student.repository.InMemoryStudentRepository;
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

        //ctx.registerShutdownHook();

        CountDownLatch latch = new CountDownLatch(1);
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}