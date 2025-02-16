package eu.balev.student;

import eu.balev.student.model.Student;
import eu.balev.student.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service("myStudentService")
public class StudentServiceImpl implements StudentService, ResourceLoaderAware {

    private final StudentRepository studentRepository;
    private final String initMessage;
    private final StudentPrinterService studentPrinterService;

    private final static String VCARD_TEMPLATE =
        """
            BEGIN:VCARD
            VERSION:3.0
            REV:%rev%
            N:%last_name%;%first_name%;;;
            BDAY:%bday%
            END:VCARD
        """;

    public StudentServiceImpl(
        @Value("${init.message}") String initMessage,
        StudentRepository studentRepository,
        StudentPrinterService studentPrinterService) {

        this.studentRepository = studentRepository;
        this.initMessage = initMessage;
        this.studentPrinterService = studentPrinterService;
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
    public String generateVCard(Student student) {
        return VCARD_TEMPLATE.
            replace("%rev%", LocalDateTime.now().toString()).
            replace("%bday%",student.birthDay().format(DateTimeFormatter.ofPattern("yyyyMMdd"))).
            replace("%last_name%", student.lastName()).
            replace("%first_name%", student.firstName());
    }

    @Override
    public void printAllStudents() {
        studentRepository.getAllStudents().forEach(studentPrinterService::printStudent);
    }

    @Override
    @PostConstruct
    public void init() {
        System.out.printf((initMessage) + "%n",
            "Student Service",
            studentRepository.count());
    }

    @PreDestroy
    @Override
    public void destroy() {
        System.out.println("Bye from student service, shutting down!");
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
}
