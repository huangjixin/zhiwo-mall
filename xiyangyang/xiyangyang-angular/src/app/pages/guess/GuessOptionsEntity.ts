export class GuessOptionsEntity {
    constructor(public id: string = '', public name: string = '', public betRate: number= 0, public realQuestionId: string= '', public isRight: boolean = false) {

    }
}
