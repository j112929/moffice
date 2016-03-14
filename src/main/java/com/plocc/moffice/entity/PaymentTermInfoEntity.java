package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/** 
 * PaymentTermInfoEntity
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月18日 上午9:49:43 
 * @email 1129290218@qq.com
 * @description  付款方式的信息
 */
public class PaymentTermInfoEntity extends Pojo{
	//付款方式名称
	private String name;
	public String getName() {
		return getString("name");
	}
	public void setName(String name) {
		set("name", name);
	}
	
}
