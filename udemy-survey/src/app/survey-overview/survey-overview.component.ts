import {Component, OnInit, signal} from '@angular/core';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import {MatToolbarModule} from '@angular/material/toolbar';
import {SurveyQuestionComponent} from '../survey-question/survey-question.component';
import {SurveyQuestionServiceService} from '../service/survey-question-service.service';
import {SurveyQuestionModel} from '../model/survey-question.model';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {SurveyAnswerModel} from '../model/survey-answer.model';


@Component({
  selector: 'app-survey-overview',
  imports: [
    MatSlideToggleModule,
    MatToolbarModule,
    MatProgressBarModule,
    SurveyQuestionComponent
  ],
  templateUrl: './survey-overview.component.html',
  styleUrl: './survey-overview.component.scss',
  standalone: true
})
export class SurveyOverviewComponent implements OnInit {

  private static initialPoints = 50;

  private answers = new Map<number, number>();

  readonly allQuestionsCompleted = signal(false)

  recommendationPoints = signal<number>(SurveyOverviewComponent.initialPoints);

  surveyQuestions = signal<SurveyQuestionModel[]>([]);

  constructor(private surveyQuestionService: SurveyQuestionServiceService) {
  }

  ngOnInit() {
    this.surveyQuestions =  this.surveyQuestionService.getSurveyQuestions();
  }

  onQuestionAnswered(answer: SurveyAnswerModel) {

    this.answers.set(answer.id, answer.score);

    // sum all answers
    let totalScore = SurveyOverviewComponent.initialPoints;
    this.answers.forEach(score => totalScore += score);

    if (totalScore < 0) {
      totalScore = 0;
    } else if (totalScore > 100) {
      totalScore = 100;
    }

    this.recommendationPoints.set(totalScore);

    if (this.answers.size === this.surveyQuestions().length) {
      this.allQuestionsCompleted.set(true);
    }
  }
}
