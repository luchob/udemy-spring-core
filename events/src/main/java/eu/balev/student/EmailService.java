package eu.balev.student;

import eu.balev.student.model.NewStudentEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @EventListener(NewStudentEvent.class)
  void onNewStudent(NewStudentEvent newStudentEvent) {
    System.out.println("Sending email to " + newStudentEvent.getNewStudent().name());
    // TODO: send an email
  }

}
