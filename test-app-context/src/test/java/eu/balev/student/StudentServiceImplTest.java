package eu.balev.student;

import static org.junit.jupiter.api.Assertions.*;

import eu.balev.TestAppConfig;
import eu.balev.student.model.Student;
import eu.balev.student.repository.StudentRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.convention.TestBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(
    classes = TestAppConfig.class
)
@ExtendWith(SpringExtension.class)
class StudentServiceImplTest {

  @Autowired
  private StudentService toTest;

  @TestBean(
      methodName="createTestStudentRepository"
  )
  private StudentRepository testRepository;

  @Test
  void findYoungestStudents() {
    Set<Student> youngestStudents = toTest.findYoungestStudents();

    Assertions.assertEquals(2, youngestStudents.size());
    Assertions.assertEquals(
        Set.of(TestStudentRepository.JOHN_SMITH_1990_3_3, TestStudentRepository.PETER_SMITH_1990_3_3),
        youngestStudents);
  }

  static StudentRepository createTestStudentRepository() {
    return new TestStudentRepository();
  }
}

class TestStudentRepository implements StudentRepository {

  static Student JOHN_SMITH_1990_3_3 = new Student("John Smith", LocalDate.of(1990, 3, 3));
  static Student PETER_SMITH_1990_3_3 = new Student("Peter Smith", LocalDate.of(1990, 3, 3));
  static Student ANNA_SMITH_1979_3_3 = new Student("Anna Smith", LocalDate.of(1979, 3, 3));

  @Override
  public List<Student> getAllStudents() {
    return List.of(JOHN_SMITH_1990_3_3, PETER_SMITH_1990_3_3, ANNA_SMITH_1979_3_3);
  }

  @Override
  public long count() {
    return 3;
  }
}