package eu.balev.student;

import eu.balev.student.model.Student;
import eu.balev.student.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

class StudentServiceImplTest {

    private StudentService toTest;

    @Test
    void testFindYoungestStudent() {

        var nina = new Student("Nina Bojinova", LocalDate.of(1977, 12, 9));
        var lachezar = new Student("Lachezar Balev", LocalDate.of(1979, 3, 7));
        var lachezar1 = new Student("Lachezar Balev Fake", LocalDate.of(1979, 3, 7));

        toTest = new StudentServiceImpl(new TestStudentRepo(List.of(lachezar, nina, lachezar1)));

        Set<Student> actual = toTest.findYoungestStudents();
        Set<Student> expected = Set.of(lachezar1, lachezar);

        Assertions.assertEquals(expected, actual);
    }

    static class TestStudentRepo implements StudentRepository {

        private final List<Student> testStudents;

        public TestStudentRepo(List<Student> testStudents) {
            this.testStudents = testStudents;
        }

        @Override
        public List<Student> getAllStudents() {
            return testStudents;
        }

        @Override
        public int countStudents() {
            return testStudents.size();
        }
    }

}
