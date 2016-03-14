/**
 * 宝龙电商
 * com.plocc.moffice.entity
 * ProductTaxInfoEntity.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/**
 * ProductTaxInfoEntity
 * @author chenyong
 * @version 1.0.0
 * @time 10:25
 * @email chenyong2@powerlong.com
 * @description 产品描述
 */
@SuppressWarnings({ "unused", "serial" })
public class ProductTaxInfoEntity extends Pojo{
	//产品描述信息
	private String description;
	
	public String getDescription() {
		return getString("description");
	}

	public void setDescription(String description) {
		set("description", description);
	}
	
	
}
