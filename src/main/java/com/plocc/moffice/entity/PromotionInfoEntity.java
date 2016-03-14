package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/** 
 * PromotionInfoEntity
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月18日 上午9:49:16 
 * @email 1129290218@qq.com
 * @description  优惠信息
 */
public class PromotionInfoEntity extends Pojo{
	//优惠描述
	private String description;
	//优惠类目id
	private int itemId;
	public String getDescription() {
		return getString("description");
	}
	public void setDescription(String description) {
		set("description", description);
	}
	public int getItemId() {
		return getInt("itemId");
	}
	public void setItemId(int itemId) {
		set("itemId", itemId);
	}
	
}
