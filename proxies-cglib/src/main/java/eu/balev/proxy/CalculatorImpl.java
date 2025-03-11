package eu.balev.proxy;

public class CalculatorImpl {

  @Cacheable("calculateNumber")
  public final double calculateNumber() {
    System.out.println("calculateNumber");
    return Math.PI;
  }
}
