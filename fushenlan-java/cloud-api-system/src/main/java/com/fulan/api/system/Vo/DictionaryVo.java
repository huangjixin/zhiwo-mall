package com.fulan.api.system.Vo;

import java.util.List;

import com.fulan.api.system.domain.Dictionary;

public class DictionaryVo {
	
	private Dictionary dictionary;
	
	
	private List<Dictionary> dictionarys;


	public Dictionary getDictionary() {
		return dictionary;
	}


	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}


	public List<Dictionary> getDictionarys() {
		return dictionarys;
	}


	public void setDictionarys(List<Dictionary> dictionarys) {
		this.dictionarys = dictionarys;
	}
	
	

}
