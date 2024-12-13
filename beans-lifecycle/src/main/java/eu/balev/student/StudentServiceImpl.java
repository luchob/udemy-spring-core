package eu.balev.student;

import eu.balev.student.model.Student;
import eu.balev.student.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service("myStudentService")
public class StudentServiceImpl implements StudentService,
    ResourceLoaderAware, BeanNameAware {

    private String beanName;
    private final String initMessage;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(
        @Value("${init.message}") String initMessage,
        @Qualifier("fileStudentRepository") StudentRepository studentRepository) {
        System.out.println("INSTANTIATION");
        this.initMessage = initMessage;
        this.studentRepository = studentRepository;
    }

    @Override
    public Set<Student> findYoungestStudents() {
        var sorted = studentRepository
                .getAllStudents()
                .stream()
                .sorted(Comparator.comparing(Student::birthDay).reversed())
                .toList();

        if (sorted.isEmpty()) {
            return Set.of();
        } else {
            LocalDate lastBirthDay = sorted.get(0).birthDay();

            return sorted
                    .stream()
                    .filter(s -> s.birthDay().equals(lastBirthDay))
                    .collect(Collectors.toSet());
        }
    }

    @Override
    @PostConstruct
    public void init() {
        System.out.printf((initMessage) + "%n",
            beanName,
            studentRepository.count());
    }

    @PreDestroy
    @Override
    public void destroy() {
        System.out.printf("Bye from %s, shutting down!%n",
            beanName);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        Resource bannerResource = resourceLoader.getResource("classpath:banner.txt");

      try {
        String banner = bannerResource.getContentAsString(StandardCharsets.UTF_8);
          System.out.println(banner);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
