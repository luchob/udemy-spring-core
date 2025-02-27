package eu.balev.proxy;

public class CalculatorImpl implements CalculatorIfc {
  @Override
  public double calculateNumber() {
    System.out.println("calculateNumber");
    return Math.PI;
  }
}
