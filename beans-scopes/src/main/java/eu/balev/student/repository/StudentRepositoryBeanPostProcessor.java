package eu.balev.student.repository;

import eu.balev.student.StudentService;
import eu.balev.student.model.Student;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class StudentRepositoryBeanPostProcessor implements BeanPostProcessor {

  public StudentRepositoryBeanPostProcessor() {
    System.out.println("In constructor of StudentRepositoryBeanPostProcessor");
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {

    if (bean instanceof StudentRepository studentRepository) {
      return new PerformanceAwareStudentRepository(studentRepository);
    }

    return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
  }
}

class PerformanceAwareStudentRepository implements StudentRepository {

  private final StudentRepository delegate;

  public PerformanceAwareStudentRepository(StudentRepository delegate) {
    this.delegate = delegate;
  }

  @Override
  public List<Student> getAllStudents() {

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    List<Student> result = delegate.getAllStudents();

    stopWatch.stop();

    System.out.println("Method getAllStudents has been executed for " +
        stopWatch.getLastTaskTimeMillis() + " millis. This is for bean " + delegate.getName());

    return result;
  }

  @Override
  public int count() {
    return delegate.count();
  }

  @Override
  public String getName() {
    return delegate.getName();
  }
}
