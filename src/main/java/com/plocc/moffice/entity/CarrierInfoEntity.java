package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/** 
 * CarrierInfoEntity
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月18日 上午9:48:59 
 * @email 1129290218@qq.com
 * @description  航空公司信息
 */
public class CarrierInfoEntity extends Pojo{
	//航空公司名称
	private String name;
	public String getName() {
		return getString("name");
	}
	public void setName(String name) {
		set("name", name);
	}
	
}
