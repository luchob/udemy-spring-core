package eu.balev.proxy;

public class Main {
  public static void main(String[] args) {
    CalculatorIfc calculatorIfc = new CacheableCalculatorProxy(new CalculatorImpl());

    System.out.println(calculatorIfc.calculateNumber());
    System.out.println(calculatorIfc.calculateNumber());
    System.out.println(calculatorIfc.calculateNumber());
    System.out.println(calculatorIfc.calculateNumber());

  }
}
