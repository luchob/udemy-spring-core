package eu.balev;

import eu.balev.monitor.PerformanceMonitor;
import org.springframework.stereotype.Component;

@Component
public class SimplePerformanceMonitor implements PerformanceMonitor {

  @Override
  public void track(String component, String operation, long millis) {
    System.out.println(
        "Component: " + component + " Operation: " + operation + " Duration: " + millis
    );
  }
}
