package eu.balev.example1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example1 {
    public static void main(String[] args) {
      AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
          "eu.balev.example1"
      );

      AppConfig1 appConfig = ctx.getBean(AppConfig1.class);

      Example1Bean example1BeanInst1 = ctx.getBean(Example1Bean.class);
      Example1Bean example1BeanInst2 = appConfig.example1Bean();

      appConfig.example1Bean();
      appConfig.example1Bean();
      appConfig.example1Bean();

      System.out.println("example1BeanInst1 == example1BeanInst2: " + (example1BeanInst1 == example1BeanInst2));

      System.out.println(appConfig.getClass());
    }

}
