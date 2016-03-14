package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/** 
 * PaymentTypeInfoEntity
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月18日 上午9:48:48 
 * @email 1129290218@qq.com
 * @description  付款类型信息
 */
public class PaymentTypeInfoEntity extends Pojo{
	//付款类型编码
	private String code;
	public String getCode() {
		return getString("code");
	}
	public void setCode(String code) {
		set("code", code);
	}
	
}
