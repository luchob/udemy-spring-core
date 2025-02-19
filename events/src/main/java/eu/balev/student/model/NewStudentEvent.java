package eu.balev.student.model;

import org.springframework.context.ApplicationEvent;

public class NewStudentEvent extends ApplicationEvent {

  private Student newStudent;

  public NewStudentEvent(Student newStudent) {

    super(newStudent);
    this.newStudent = newStudent;
  }

  public Student getNewStudent() {
    return newStudent;
  }
}
