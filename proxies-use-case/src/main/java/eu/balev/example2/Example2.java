package eu.balev.example2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example2 {
    public static void main(String[] args) {
      AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
          "eu.balev.example2"
      );

      CalculatorIfc calculator = ctx.getBean(CalculatorIfc.class);

      System.out.println("----------");

      System.out.println(calculator.calculateNumber());
      System.out.println(calculator.calculateNumber());
      System.out.println(calculator.calculateNumber());
      System.out.println(calculator.calculateNumber());


      System.out.println(calculator.getClass());
    }

}
