package eu.balev.student.config;

import eu.balev.student.StudentService;
import eu.balev.student.StudentServiceImpl;
import eu.balev.student.repository.FileStudentRepository;
import eu.balev.student.repository.InMemoryStudentRepository;
import eu.balev.student.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

  @Bean
  public StudentRepository fileStudentRepository() {
    return new FileStudentRepository();
  }

  @Bean
  public StudentRepository inMemoryStudentRepository() {
    return new InMemoryStudentRepository();
  }

  @Bean
  public StudentService studentService(
      List<StudentRepository> studentRepositories,
      @Value("${init.message}") String initMessage) {
    return new StudentServiceImpl(studentRepositories,
        initMessage);
  }

}
