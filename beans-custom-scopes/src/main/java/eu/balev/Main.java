package eu.balev;


import eu.balev.student.StudentNameService;
import eu.balev.student.StudentService;
import eu.balev.student.util.StopWatch;
import eu.balev.student.util.ThreadScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
            "eu.balev"
        );

        ctx.registerShutdownHook();
        ctx.getBeanFactory().registerScope("thread",
            new ThreadScope());

        StudentService studentService = ctx.getBean(StudentService.class);
        StopWatch stopWatch = ctx.getBean(StopWatch.class);

        System.out.println("----");
        stopWatch.start();
        System.out.println(studentService.findYoungestStudents());
        System.out.println(studentService.findYoungestStudents());
        System.out.println(studentService.findYoungestStudents());
        System.out.println(studentService.findYoungestStudents());
        stopWatch.stop();
        stopWatch.printStat();
        stopWatch.reset();
        System.out.println("----");

        stopWatch = ctx.getBean(StopWatch.class);
        stopWatch.start();
        System.out.println(studentService.findYoungestStudents());
        System.out.println(studentService.findYoungestStudents());
        System.out.println(studentService.findYoungestStudents());
        System.out.println(studentService.findYoungestStudents());
        stopWatch.stop();
        stopWatch.printStat();
        stopWatch.reset();

        System.out.println("----");

        Thread studentThread = new Thread(
            () -> {
                System.out.println("----");
                StopWatch threadStopWatch = ctx.getBean(StopWatch.class);
                threadStopWatch.start();
                System.out.println(studentService.findYoungestStudents());
                System.out.println(studentService.findYoungestStudents());
                System.out.println(studentService.findYoungestStudents());
                System.out.println(studentService.findYoungestStudents());
                threadStopWatch.stop();
                threadStopWatch.printStat();
                threadStopWatch.reset();
                System.out.println("----");

                threadStopWatch = ctx.getBean(StopWatch.class);
                threadStopWatch.start();
                System.out.println(studentService.findYoungestStudents());
                System.out.println(studentService.findYoungestStudents());
                System.out.println(studentService.findYoungestStudents());
                System.out.println(studentService.findYoungestStudents());
                threadStopWatch.stop();
                threadStopWatch.printStat();
                threadStopWatch.reset();
                System.out.println("----");
            }
        );

        studentThread.start();
        studentThread.join();
    }
}