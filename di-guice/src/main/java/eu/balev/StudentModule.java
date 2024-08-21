package eu.balev;

import com.google.inject.AbstractModule;
import eu.balev.student.StudentService;
import eu.balev.student.StudentServiceImpl;
import eu.balev.student.repository.FileStudentRepository;
import eu.balev.student.repository.InMemoryStudentRepository;
import eu.balev.student.repository.StudentRepository;

public class StudentModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(StudentRepository.class).to(FileStudentRepository.class);
    bind(StudentService.class).to(StudentServiceImpl.class);
  }
}
