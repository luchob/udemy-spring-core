import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {SurveyOverviewComponent} from './survey-overview/survey-overview.component';

@Component({
  selector: 'app-root',
  imports: [SurveyOverviewComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  standalone: true
})
export class AppComponent {
  title = 'udemy-survey';
}
