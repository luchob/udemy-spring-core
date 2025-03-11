import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MatRadioModule} from '@angular/material/radio';
import {SurveyQuestionModel} from '../model/survey-question.model';
import {SurveyAnswerModel} from '../model/survey-answer.model';

@Component({
  selector: 'app-survey-question',
  imports: [MatRadioModule],
  templateUrl: './survey-question.component.html',
  styleUrl: './survey-question.component.scss',
  standalone: true
})
export class SurveyQuestionComponent {

  @Input() surveyQuestion?: SurveyQuestionModel;

  @Output() newSelection = new EventEmitter<SurveyAnswerModel>();

  onSelectionChange($event: any) {
    if (this.surveyQuestion) {
      this.newSelection.emit(new SurveyAnswerModel(this.surveyQuestion.id, $event.value));
    }
  }
}
