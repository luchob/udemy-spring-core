package eu.balev.student.config;

import eu.balev.student.StudentService;
import eu.balev.student.StudentServiceImpl;
import eu.balev.student.repository.FileStudentRepository;
import eu.balev.student.repository.InMemoryStudentRepository;
import eu.balev.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Bean(
       name = "fileRepo"
    )
    public StudentRepository fileStudentRepository() {
        return new FileStudentRepository();
    }

    @Bean(
            name = "inMemoryRepo"
    )
    public StudentRepository inMemoryStudentRepository() {
        return new InMemoryStudentRepository();
    }

    @Bean
    public StudentService studentService(@Qualifier("fileRepo") StudentRepository studentRepository) {
        return new StudentServiceImpl(studentRepository);
    }
}
