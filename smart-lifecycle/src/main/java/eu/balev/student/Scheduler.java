package eu.balev.student;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Scheduler implements SmartLifecycle,
    BeanPostProcessor {

  private volatile boolean running = false;

  private ScheduledExecutorService scheduler;


  @Override
  public void start() {
    if (!running) {
      System.out.println("Starting the scheduler...");
      running = true;
      scheduler = Executors.newScheduledThreadPool(1);
      scheduler.scheduleAtFixedRate(() -> {
        System.out.println("Scheduled task is running...");
      }, 0, 5, TimeUnit.SECONDS);
    }
  }

  @Override
  public void stop() {
    if (running) {
      System.out.println("Stopping the scheduler...");
      running = false;
      scheduler.shutdownNow();
    }
  }

  @Override
  public boolean isRunning() {
    return running;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
  }

  @EventListener
  void listenForStudentEvents(ApplicationEvent applicationEvent) {
    System.out.println("Received application event: " + applicationEvent.getClass());
  }
}
