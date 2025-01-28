package eu.balev.monitor;

public interface PerformanceMonitor {
  void track(String component, String operation, long millis);
}
