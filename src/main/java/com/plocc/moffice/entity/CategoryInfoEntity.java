/**
 * 宝龙电商
 * com.plocc.moffice.entity
 * CategoryInfoEntity.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/**
 * CategoryInfoEntity
 * @author chenyong
 * @version 1.0.0
 * @time 10:05
 * @email chenyong2@powerlong.com
 * @description 产品类别
 */
@SuppressWarnings({ "unused", "serial" })
public class CategoryInfoEntity extends Pojo{
	//产品别名称
	private String name;
	//是否有子节点
	private Boolean isLeaf;
	
	public String getName() {
		return getString("name");
	}
	
	public void setName(String name) {
		set("name", name);
	}
	
	public Boolean getIsLeaf() {
		return getBoolean("isLeaf");
	}
	
	public void setIsLeaf(Boolean isLeaf) {
		set("isLeaf", isLeaf);
	}
	
}
