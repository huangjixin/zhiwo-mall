package com.fulan.application.oa.constant;

public enum ApplyAction {
	Agree("agree"),
	Reject("reject"),
	;
	
	private ApplyAction(String name) {
		this.name= name;
	}
	
	public static ApplyAction getAction(String name) {
		for (ApplyAction action : ApplyAction.values()) {
            if (action.getName().equals(name)) {
                return action;
            }
        }
		return null;
	}
	private String name;
	
	public String getName() {
		return this.name;
	}
}
