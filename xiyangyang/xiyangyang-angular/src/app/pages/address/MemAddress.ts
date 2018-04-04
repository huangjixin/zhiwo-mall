export class MemAddress{
  constructor(public id: string = '',
              public province: string = '',
              public city: string = '',
              public area: string = '',
              public street: string = '',
              public name: string = '',
              public mobilPhone: string = '',
              public memberId: string = '',
              public isDefault: string = '',
              public enabled: string = '',
              public createDate: Date = new Date(),
              public updateDate: Date  = new Date()) {
  }
}
