package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/** 
 * ChannelInfoEntity
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月18日 上午9:48:34 
 * @email 1129290218@qq.com
 * @description  渠道信息
 */
public class ChannelInfoEntity extends Pojo{
	private String name;
	private String status;
	private String type;
	private boolean isDefault;
	private Object[] warehouses;
	private Object[] skus;
	public String getName() {
		return getString("name");
	}
	public void setName(String name) {
		set("name", name);
	}
	public String getStatus() {
		return getString("status");
	}
	public void setStatus(String status) {
		set("status", status);
	}
	public String getType() {
		return getString("type");
	}
	public void setType(String type) {
		set("type", type);
	}
	public boolean isDefault() {
		return getBoolean("isDefault");
	}
	public void setDefault(boolean isDefault) {
		set("isDefault", isDefault);
	}
	public Object[] getWarehouses() {
		return (Object[]) get("warehouses");
	}
	public void setWarehouses(Object[] warehouses) {
		set("warehouses", warehouses);
	}
	public Object[] getSkus() {
		return (Object[]) get("skus");
	}
	public void setSkus(Object[] skus) {
		set("skus", skus);
	}
	
}
