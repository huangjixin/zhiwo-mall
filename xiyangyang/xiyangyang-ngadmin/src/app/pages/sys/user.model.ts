import {Role} from './role.model';

export class User {
  constructor() {}

  roles: Array<Role>;

  id: string;
  username: string;
  loginDate: Date;
  createDate: Date;
  updateDate: Date;
  lastLoginDate: Date;
  email: string;
  mobilPhone: string;
  enabled: Boolean;
  creator: string;
  updater: string;
  sex: Boolean;
  icon: string;
  description: string;
  age: Number;
  weight: Number;
  qq: string;
  weixin: string;
  realName: string;
  loginCount: Number;
  sort: Number;
  orgId: string;
  parentId: string;
  parentids: string;
  memberGroupId: string;
  nickname: string;
  loginName: string;
  usergroupId: string;
  emergecyContact: string;
  isCertificateInternational: Boolean;
  idCard: string;
  coopName: string;
  coopAddress: string;
  bussinessLicenseCode: string;
  orgCode: string;
  taxpayer: string;
  societyCode: string;
  legalRepresentativeCard1: string;
  legalRepresentativeCard2: string;
  legalIdcardEffitive: string;
  bussinessLicensePic: string;
  licenseForOpeningCount: string;
  quantityReportId: string;
  type: string;
}
