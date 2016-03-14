package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/** 
 * CustomerInfoEntity
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月18日 上午9:46:40 
 * @email 1129290218@qq.com
 * @description  客户信息
 */
public class CustomerInfoEntity extends Pojo{
	//客户名称
	private String name;
	//客户编号
	private String code;
	public String getName() {
		return getString("name");
	}
	public void setName(String name) {
		set("name", name);
	}
	public String getCode() {
		return getString("code");
	}
	public void setCode(String code) {
		set("code", code);
	}
	
}
