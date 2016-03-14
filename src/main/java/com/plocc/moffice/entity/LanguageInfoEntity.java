package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

public class LanguageInfoEntity extends Pojo {
	private String code;

	public String getCode() {
		return getString("code");
	}

	public void setCode(String code) {
		set("code", code);
	}
	
}
