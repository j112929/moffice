package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/** 
 * CurrencyInfoEntity
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月18日 上午9:49:31 
 * @email 1129290218@qq.com
 * @description  货币信息
 */
public class CurrencyInfoEntity extends Pojo{
	//汇率
	private int exchangeRate;
	//货币编码
	private String code;
	//货币ISO编码
	private String isoCode;
	public int getExchangeRate() {
		return getInt(exchangeRate);
	}
	public void setExchangeRate(int exchangeRate) {
		set("exchangeRate", exchangeRate);
	}
	public String getCode() {
		return getString("code");
	}
	public void setCode(String code) {
		set("code", code);
	}
	public String getIsoCode() {
		return getString("isoCode");
	}
	public void setIsoCode(String isoCode) {
		set("isoCode", isoCode);
	}
	
	
}
