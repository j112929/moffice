package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/** 
 * BaseDocumentInfo
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月23日 下午5:26:20 
 * @email 1129290218@qq.com
 * @description  基础文档信息
 */
public class BaseDocumentInfo extends Pojo {
	private int baseId;
	private int baseLineId;
	private String baseNumber;
	private String baseType;
	private int baseLineNumber;
	public int getBaseId() {
		return getInt("baseId");
	}
	public void setBaseId(int baseId) {
		set("baseId", baseId);
	}
	public int getBaseLineId() {
		return getInt("baseLineId");
	}
	public void setBaseLineId(int baseLineId) {
		set("baseLineId", baseLineId);
	}
	public String getBaseNumber() {
		return getString("baseNumber");
	}
	public void setBaseNumber(String baseNumber) {
		set("baseNumber", baseNumber);
	}
	public String getBaseType() {
		return getString("baseType");
	}
	public void setBaseType(String baseType) {
		set("baseType", baseType);
	}
	public int getBaseLineNumber() {
		return getInt("baseLineNumber");
	}
	public void setBaseLineNumber(int baseLineNumber) {
		set("baseLineNumber", baseLineNumber);
	}
	
}
