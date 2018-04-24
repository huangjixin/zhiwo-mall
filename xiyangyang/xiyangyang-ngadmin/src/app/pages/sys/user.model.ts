import {Role} from './role.model';

export class User {
  constructor() {}

  roles: Array<Role>;

  id: String;
  username: String;
  loginDate: Date;
  createDate: Date;
  updateDate: Date;
  lastLoginDate: Date;
  email: String;
  mobilPhone: String;
  enabled: Boolean;
  creator: String;
  updater: String;
  sex: Boolean;
  icon: String;
  description: String;
  age: Number;
  weight: Number;
  qq: String;
  weixin: String;
  realName: String;
  loginCount: Number;
  sort: Number;
  orgId: String;
  parentId: String;
  parentids: String;
  memberGroupId: String;
  nickname: String;
  loginName: String;
  usergroupId: String;
  emergecyContact: String;
  isCertificateInternational: Boolean;
  idCard: String;
  coopName: String;
  coopAddress: String;
  bussinessLicenseCode: String;
  orgCode: String;
  taxpayer: String;
  societyCode: String;
  legalRepresentativeCard1: String;
  legalRepresentativeCard2: String;
  legalIdcardEffitive: String;
  bussinessLicensePic: String;
  licenseForOpeningCount: String;
  quantityReportId: String;
  type: String;
}
