package eu.balev;

import eu.balev.proxy.CacheableEnhancer;
import eu.balev.proxy.CalculatorImpl;

public class Main {

  public static void main(String[] args)
      throws Exception {

    CalculatorImpl calculator = getBean(CalculatorImpl.class);

    System.out.println(calculator.calculateNumber());
    System.out.println(calculator.calculateNumber());
    System.out.println(calculator.calculateNumber());

  }

  private static <T> T getBean(Class<T> clazz) throws Exception {
    CacheableEnhancer<T> cacheableEnhancer = new CacheableEnhancer<>(clazz.getDeclaredConstructor().newInstance());
    return cacheableEnhancer.enhance();
  }

}
