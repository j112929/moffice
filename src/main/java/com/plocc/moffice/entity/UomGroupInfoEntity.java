/**
 * 宝龙电商
 * com.plocc.moffice.entity
 * UomGroupInfoEntity.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/**
 * UomGroupInfoEntity
 * @author chenyong
 * @version 1.0.0
 * @time 11:35
 * @email chenyong2@powerlong.com
 * @description 产品计量单位
 */
@SuppressWarnings({ "unused", "serial" })
public class UomGroupInfoEntity extends Pojo{
	//名称
	private String name;
	
	public String getName() {
		return getString("name");
	}
	
	public void setName(String name) {
		set("name", name);
	}
	
}
