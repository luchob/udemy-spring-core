package eu.balev;

import eu.balev.proxy.CacheableEnhancer;
import eu.balev.proxy.CalculatorImpl;
import java.lang.reflect.InvocationTargetException;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class Main {

  public static void main(String[] args)
      throws Exception {

    CacheableEnhancer<CalculatorImpl> enhancer = new CacheableEnhancer<>(new CalculatorImpl());
    CalculatorImpl calculator = enhancer.enhance();

    System.out.println(calculator.calculateNumber());
    System.out.println(calculator.calculateNumber());
    System.out.println(calculator.calculateNumber());

  }

}
