export class ProCategory {

     id: String;

     creator: String;

     updater: String;

     orgId: String;

     createDate: Date;

     updateDate: Date;

     enabled: Boolean;

     startTime: Date;

     endTime: Date;

     sort: Number;

     isDefault: Boolean;

     enName: String;

     name: String;

     description: String;

     parentId: String;

     parentids: String;

     icon: String;

     keywords: String;

    /**
     * 缩略图
     */
     thumbnail: String;

    /**
     * 代码
     */
     code: String;

    parent: ProCategory;

    children: Array<ProCategory>;

    constructor() {}

}