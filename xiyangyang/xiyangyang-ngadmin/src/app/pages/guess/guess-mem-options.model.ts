export class GuessMemOptions {

  id: String;

  /**
   * 名称竞猜选项ID
   */
  optionId: String;

  /**
   * 会员ID
   */
  memId: String;

  /**
   * 竞猜问题ID
   */
  questionId: String;

  /**
   * 下注数量
   */
  betValue: number;

  /**
   * 创建日期
   */
  createDate: Date;

  guessOptionsCombineId: String;

  constructor() {}
}
