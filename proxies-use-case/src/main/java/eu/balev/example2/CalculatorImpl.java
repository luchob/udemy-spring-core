package eu.balev.example2;

import org.springframework.cache.annotation.Cacheable;

public class CalculatorImpl implements CalculatorIfc {

  @Cacheable("calculateNumber")
  @Override
  public double calculateNumber() {
    System.out.println("calculateNumber");
    return Math.PI;
  }
}
