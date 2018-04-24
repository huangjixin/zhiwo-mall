export class GuessOptions {

  id: String;

  /**
   * 名称
   */
  name: String;

  /**
   * 赔率
   */
  betRate: number;

  /**
   * 竞猜问题ID
   */
  guessQuestionId: String;

  /**
   * 正式的外键ID
   */
  realQuestionId: String;

  isRight: Boolean;
  constructor() {}
}
