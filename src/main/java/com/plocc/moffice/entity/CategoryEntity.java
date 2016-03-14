/**
 * 宝龙电商
 * com.plocc.moffice.entity
 * CategoryEntity.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;
import com.plocc.framework.support.JsonpHelper;

/**
 * CategoryEntity
 * @author chenyong
 * @version 1.0.0
 * @time 09:53
 * @email chenyong2@powerlong.com
 * @description 产品类别
 */
@SuppressWarnings({ "unused", "serial" })
public class CategoryEntity extends Pojo {
	//产品类别名称
	private String name;
	//是否有子节点
	private Boolean isLeaf;
	//父节点
	private CategoryInfoEntity parent;
	//创建时间
	private String creationTime;
	//更新时间
	private String updateTime;
	
	
	
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
	
	public CategoryInfoEntity getParent() {
	    return JsonpHelper.toObject(JsonpHelper.toString(this.get("parent")), CategoryInfoEntity.class);
	}
	
	public void setParent(CategoryInfoEntity parent) {
		set("parent", parent);
	}
	
	public String getCreationTime() {
		return getString("creationTime");
	}
	
	public void setCreationTime(String creationTime) {
		set("creationTime", creationTime);
	}
	
	public String getUpdateTime() {
		return getString("updateTime");
	}
	
	public void setUpdateTime(String updateTime) {
		set("updateTime", updateTime);
	}
	
	
}
