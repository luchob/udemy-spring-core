import {Injectable, signal} from '@angular/core';
import {SurveyQuestionModel} from '../model/survey-question.model';

@Injectable({
  providedIn: 'root'
})
export class SurveyQuestionServiceService {

  private surveyQuestions: SurveyQuestionModel[] = [
    new SurveyQuestionModel(
      1,
      "What is your programming experience?",
      "None.",
      -30,
      "Very little, just starting.",
      0,
      "I think I'm mid level.",
      10,
      "I'm an experienced developer.",
      10
    ),
    new SurveyQuestionModel(
      2,
      "Do you have knowledge of Java or Kotlin programming languages?",
      "No.",
      -20,
      "Yes.",
      0
    ),
    new SurveyQuestionModel(
      3,
      "Do you have previous experience with the Spring Framework?",
      "No, these are my first steps.",
      10,
      "Just a little.",
      10,
      "Yes, I have experience.",
      10,
      "Yes, I'm very advanced user.",
      -10
    ),
    new SurveyQuestionModel(
      4,
      "Which of the following best describes your expectations from this course?",
      "I expect a zero-to-hero experience and I want to be able to create complex Spring applications after finishing this course.",
      -20,
      "I expect to learn a lot about Spring boot and be able to create a full blown Spring application.",
      -20,
      "I expect to learn more details about the fundamentals of the Spring framework. Mostly about the spring beans, dependency injection, etc.",
      30,
      "I expect to know more about AOP after finishing the course.",
      0
    ),
    new SurveyQuestionModel(
      5,
      "Do you have an experience with the JUnit test framework?",
      "Yes.",
      0,
      "No.",
      -10,
    )
    ,
    new SurveyQuestionModel(
      6,
      "Do you have an experience with building tools like Gradle or Maven?",
      "Yes.",
      0,
      "No.",
      -10,
    ),
    new SurveyQuestionModel(
      7,
      "Do you understand what inversion of control and dependency injection is?",
      "Yes.",
      0,
      "No.",
      10,
    )
    ,
    new SurveyQuestionModel(
      8,
      "Do you know that Spring beans have a lifecycle and you can attach to different lifecycle stages?",
      "Yes.",
      0,
      "No.",
      10,
    )
    ,
    new SurveyQuestionModel(
      9,
      "How can you configure a spring application context?",
      "I do not know.",
      20,
      "With spring boot.",
      10,
      "With java annotations.",
      10,
      "With XML configuration or 'bytecode' configuration like stereotype annotations and java configuration.",
      -10
    )
    ,
    new SurveyQuestionModel(
      10,
      "What is the default scope of spring managed beans?",
      "They do not have scopes.",
      20,
      "I have no idea what scope is.",
      20,
      "They are all just singletons.",
      10,
      "Singleton.",
      -10
    )
  ];

  private _allQuestions = signal<SurveyQuestionModel[]>(this.surveyQuestions);

  // create constant with question data


  constructor() {
  }

  public getSurveyQuestions() {
    return this._allQuestions;
  }

}
