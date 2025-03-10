package eu.balev;

import eu.balev.proxy.CacheableEnhancer;
import eu.balev.proxy.CalculatorImpl;
import java.lang.reflect.InvocationTargetException;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class Main {

  public static void main(String[] args)
      throws Exception {

    CalculatorImpl calculator = createProxy(CalculatorImpl.class);

    System.out.println(calculator.calculateNumber());
    System.out.println(calculator.calculateNumber());
    System.out.println(calculator.calculateNumber());

  }

  private static <T> T createProxy(Class<T> clazz) throws Exception {
    CacheableEnhancer<T> enhancer = new CacheableEnhancer<>(clazz.getDeclaredConstructor().newInstance());
    return enhancer.enhance();
  }

}
