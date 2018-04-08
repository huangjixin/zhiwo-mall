import {GuessOptionsEntity} from './GuessOptionsEntity';

export class GuessQuestionEntity{
  constructor(public id:string,public creator:string,public updater:string,public orgId:string,public createDate:Date,public updateDate:Date,
              public enabled:boolean,public startTime:Date,public endTime:Date,public sort:number,public isDefault:boolean,
              public enName:string,public name:string,public description:string,public icon:string,public keywords:string,
              public thumbnail:string,
              public code:string,
              public userId:string,
              public guessCategoryId:string,
              public guessAnswerId:string,
              public questionEndTime:Date,
              public checked:boolean,public guessOptions:Array<GuessOptionsEntity>){
  }
}
