package eu.balev.student.util;

public class StopWatch {

  private long start = -1, stop = -1;

  public void start() {
    if (start != -1) {
      throw new IllegalStateException("Already started");
    }
    start = System.currentTimeMillis();
  }

  public void stop() {
    if (stop != -1) {
      throw new IllegalStateException("Already stopped");
    }
    stop = System.currentTimeMillis();
  }

  public void reset() {
    start = -1;
    stop = -1;
  }

  public void printStat() {

    if (stop == -1 || start == -1) {
      throw new IllegalStateException("Not used correctly.");
    }

    System.out.println("Total time: " + (stop - start));
    System.out.println("Thread: " + Thread.currentThread().getId());
    System.out.println("StopWatch: " + hashCode());

  }
}
