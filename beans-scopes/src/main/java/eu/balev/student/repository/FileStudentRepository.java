package eu.balev.student.repository;

import eu.balev.student.model.Student;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository("fileRepo")
public class FileStudentRepository implements StudentRepository, InitializingBean, DisposableBean,
    BeanNameAware, ResourceLoaderAware {

    private String beanName;

    private ResourceLoader resourceLoader;

    public FileStudentRepository() {
        System.out.println("FileStudentRepository instantiated");
    }

    @Override
    public List<Student> getAllStudents() {

      Resource studentCSVResource = resourceLoader.getResource("students.csv");

      try {
        return studentCSVResource
            .getContentAsString(StandardCharsets.UTF_8)
            .lines()
            .map(this::asStudent)
            .toList();
      } catch (IOException e) {
        e.printStackTrace();
        return List.of();
      }
    }

    private Student asStudent(String s) {
        var line = s.split(",");
        return new Student(line[0].trim(), LocalDate.parse(line[1].trim()));
    }

    @Override
    public int count() {
        return getAllStudents().size();
    }

    @Override
    public String getName() {
        return beanName;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("File repo manages " + count() + "students. Cleanup complete. Bye!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(getName() + " manages " + count() + "students. Init complete. Hello!");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
