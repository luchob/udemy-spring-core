export class SurveyQuestionModel {

  id: number;
  question: string;
  answer1: string;
  score1: number;
  answer2: string;
  score2: number;
  answer3?: string;
  score3?: number;
  answer4?: string;
  score4?: number;

  constructor(id: number, question: string, answer1: string, score1: number, answer2: string, score2: number, answer3?: string, score3?: number, answer4?: string, score4?: number) {
    this.id = id;
    this.question = question;
    this.answer1 = answer1;
    this.score1 = score1;
    this.answer2 = answer2;
    this.score2 = score2;
    this.answer3 = answer3;
    this.score3 = score3;
    this.answer4 = answer4;
    this.score4 = score4;
  }
}
