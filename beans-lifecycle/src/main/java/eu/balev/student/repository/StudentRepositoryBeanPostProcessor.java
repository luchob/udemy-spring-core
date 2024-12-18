package eu.balev.student.repository;

import eu.balev.student.model.Student;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class StudentRepositoryBeanPostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

    if (bean instanceof StudentRepository studentRepository) {
      return new StudentRepositoryStatsProxy(studentRepository, beanName);
    }

    return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
  }
}

class StudentRepositoryStatsProxy implements StudentRepository {

  private final StudentRepository delegate;
  private final String beanName;

  private AtomicInteger cntGetAll = new AtomicInteger(0);
  private AtomicInteger cntCount = new AtomicInteger(0);

  public StudentRepositoryStatsProxy(StudentRepository delegate, String beanName) {
    this.delegate = delegate;
    this.beanName = beanName;
  }

  @Override
  public List<Student> getAllStudents() {

    System.out.println(beanName + "#getAllStudents called " + cntGetAll.incrementAndGet() + " times.");

    return delegate.getAllStudents();
  }

  @Override
  public long count() {

    System.out.println(beanName + "#count called " + cntCount.incrementAndGet() + " times.");

    return delegate.count();
  }
}
