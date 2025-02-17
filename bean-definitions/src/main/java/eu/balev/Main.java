package eu.balev;

import eu.balev.student.StudentService;
import eu.balev.student.StudentServiceImpl;
import eu.balev.student.model.Student;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
            "eu.balev"
        );

        ctx.registerShutdownHook();

        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) ctx.getBeanFactory();

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(StudentServiceImpl.class);
        beanDefinition.setScope("singleton");

        beanFactory.registerBeanDefinition("studentServiceImpl", beanDefinition);


        StudentService studentService1 = ctx.getBean(StudentService.class);
        StudentService studentService2 = ctx.getBean(StudentService.class);

        System.out.println(studentService1 == studentService2);

//        for (Student youngestStudent : studentService.findYoungestStudents()) {
//            System.out.println(youngestStudent);
//        }
    }

}