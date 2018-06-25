package com.fulan.application.oa.constant;

public enum ApplyTypeEnum {
	
	RESIGN("Resign","0"),//离职
	LEAVE("Leave","1"),//请假
	Promotion("Promotion","2"),//晋升
	Check("Check","3"),//复效
	AddressUpdate("AddressUpdate","4"),//地址变更
	MobileUpdate("MobileUpdate","5"),//手机号卡变更
	BankCardUpdate("BankCardUpdate","6"),//银行卡变更
	IncomeProvment("IncomeProvment","7"),//收入证明
	WorkProvment("WorkProvment","8"),//工作证明
	OtherProvment("OtherProvment","9"),//其它收入证明
	;

	private String name;
	private String code;
	
	private ApplyTypeEnum(String name,String code) {
		this.name = name;
		this.code = code;
	}

	public static ApplyTypeEnum getByCode(String code) {
        for (ApplyTypeEnum type : ApplyTypeEnum.values()) {
            if (type.getCode().equals( code )) {
                return type;
            }
        }
        return null;
    }

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
	

	
	
}
