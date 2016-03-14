/**
 * 宝龙电商
 * com.plocc.moffice.entity
 * ManufacturerInfoEntity.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/**
 * ManufacturerInfoEntity
 * @author chenyong
 * @version 1.0.0
 * @time 10:45
 * @email chenyong2@powerlong.com
 * @description 产品制造商
 */
@SuppressWarnings({ "unused", "serial" })
public class ManufacturerInfoEntity extends Pojo{
	//制造商名称
	private String name;
	
	
	public String getName() {
		return getString("name");
	}
	
	public void setName(String name) {
		set("name", name);
	}
	
}
