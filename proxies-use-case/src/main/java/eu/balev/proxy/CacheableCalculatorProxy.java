package eu.balev.proxy;

public class CacheableCalculatorProxy implements CalculatorIfc {

  private final CalculatorIfc delegate;

  private volatile boolean isCached = false;
  private volatile double cachedValue = 0;

  public CacheableCalculatorProxy(CalculatorIfc delegate) {
    this.delegate = delegate;
  }

  @Override
  public double calculateNumber() {
    if (!isCached) {
      cachedValue = delegate.calculateNumber();
      isCached = true;
    }
    return cachedValue;
  }
}
