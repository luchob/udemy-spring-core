package eu.balev;

import com.google.inject.Guice;
import com.google.inject.Injector;
import eu.balev.student.StudentModule;
import eu.balev.student.StudentService;

public class Main {
    public static void main(String[] args) {

        Injector injector = Guice
                .createInjector(new StudentModule());

        StudentService studentService =
                injector.getInstance(StudentService.class);


        System.out.println(studentService.findYoungestStudents());
    }
}