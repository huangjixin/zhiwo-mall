import {GuessCategory} from './guess-category.model';

export class GuessQuestion {

  guessCategory : GuessCategory;
  guessOptions : Array < GuessQuestion >;

  id : String;

  creator : String;

  updater : String;

  orgId : String;

  createDate : Date;

  updateDate : Date;

  enabled : Boolean;

  startTime : Date;

  endTime : Date;

  sort : number;

  isDefault : Boolean;

  enName : String;

  name : String;

  description : String;

  icon : String;

  keywords : String;

  thumbnail : String;

  code : String;

  userId : String;

  guessCategoryId : String;

  guessAnswerId : String;

  questionEndTime : Date;

  checked : Number;

  parentId : String;
  constructor() {}
}
