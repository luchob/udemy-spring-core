package eu.balev.student;

import eu.balev.student.model.NewStudentEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppContextEventListener {

  @EventListener(ApplicationEvent.class)
  public void onApplicationEvent(ApplicationEvent applicationEvent) {
    if (!(applicationEvent instanceof NewStudentEvent)) {
      System.out.println("-----");
      System.out.println(applicationEvent.getSource());
      System.out.println(applicationEvent.getClass());
      System.out.println("-----");
    }
  }

}
