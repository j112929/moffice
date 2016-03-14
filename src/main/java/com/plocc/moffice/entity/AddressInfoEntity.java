package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/** 
 * AddressInfoEntity
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月18日 上午9:49:56 
 * @email 1129290218@qq.com
 * @description  地址信息
 */
public class AddressInfoEntity extends Pojo{
	//国家编号
	private String countryCode;
	//地区编号
	private String stateCode;
	//地区
	private String state;
	//城市名
	private String cityName;
	//街道1
	private String street1;
	//街道2
	private String street2;
	//邮编
	private String zipCode;
	//手机
	private String mobile;
	//电话
	private String telephone;
	//收件人姓名
	private String recipientName;
	//展示名
	private String displayName;
	public String getCountryCode() {
		return getString("countryCode");
	}
	public void setCountryCode(String countryCode) {
		set("countryCode", countryCode);
	}
	public String getStateCode() {
		return getString("stateCode");
	}
	public void setStateCode(String stateCode) {
		set("stateCode", stateCode);
	}
	public String getState() {
		return getString("state");
	}
	public void setState(String state) {
		set("state", state);
	}
	public String getCityName() {
		return getString("cityName");
	}
	public void setCityName(String cityName) {
		set("cityName", cityName);
	}
	public String getStreet1() {
		return getString("street1");
	}
	public void setStreet1(String street1) {
		set("street1", street1);
	}
	public String getStreet2() {
		return getString("street2");
	}
	public void setStreet2(String street2) {
		set("street2", street2);
	}
	public String getZipCode() {
		return getString("zipCode");
	}
	public void setZipCode(String zipCode) {
		set("zipCode", zipCode);
	}
	public String getMobile() {
		return getString("mobile");
	}
	public void setMobile(String mobile) {
		set("mobile", mobile);
	}
	public String getTelephone() {
		return getString("telephone");
	}
	public void setTelephone(String telephone) {
		set("telephone", telephone);
	}
	public String getRecipientName() {
		return getString("recipientName");
	}
	public void setRecipientName(String recipientName) {
		set("recipientName", recipientName);
	}
	public String getDisplayName() {
		return getString("displayName");
	}
	public void setDisplayName(String displayName) {
		set("displayName", displayName);
	}
	
}
