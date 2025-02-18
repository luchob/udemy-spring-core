package eu.balev;

import eu.balev.student.StudentService;
import eu.balev.student.StudentServiceImpl;
import eu.balev.student.model.Student;
import eu.balev.student.repository.FileStudentRepository;
import eu.balev.student.repository.InMemoryStudentRepository;
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

        ctx.registerShutdownHook();

        GenericBeanDefinition fileStudentRepositoryDef = new GenericBeanDefinition();
        fileStudentRepositoryDef.setBeanClass(FileStudentRepository.class);
        fileStudentRepositoryDef.setPrimary(true);

        GenericBeanDefinition inMemoryStudentRepositoryDef = new GenericBeanDefinition();
        inMemoryStudentRepositoryDef.setBeanClass(InMemoryStudentRepository.class);

        GenericBeanDefinition studentServiceDef = new GenericBeanDefinition();
        studentServiceDef.setBeanClass(StudentServiceImpl.class);
        studentServiceDef.setScope("prototype");

        BeanDefinitionRegistry beanBeanDefinitionRegistry = (BeanDefinitionRegistry)ctx.getBeanFactory();
        beanBeanDefinitionRegistry.registerBeanDefinition("fileStudentRepository", fileStudentRepositoryDef);
        beanBeanDefinitionRegistry.registerBeanDefinition("studentServiceImpl", studentServiceDef);
        beanBeanDefinitionRegistry.registerBeanDefinition("inMemoryStudentRepositoryDef", inMemoryStudentRepositoryDef);
        //
        StudentService studentService1 = ctx.getBean(StudentService.class);
        StudentService studentService2 = ctx.getBean(StudentService.class);

        System.out.println(studentService1 == studentService2);

//        for (Student youngestStudent : studentService.findYoungestStudents()) {
//            System.out.println(youngestStudent);
//        }
    }

}