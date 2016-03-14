package com.plocc.moffice.entity;

import java.util.Map;

import com.plocc.framework.entity.Pojo;

/**
 * 仓库实体
 * 
 * @author Administrator
 *
 */
@SuppressWarnings({"unused", "rawtypes"})
public class WarehouseInfoEntity extends Pojo {
    
    private static final long serialVersionUID = 1L;
    
    //仓库名
    private String name;
	//仓库编号
	private String code;
	// 仓库状态
	private String status;
	
	
    public WarehouseInfoEntity() {
    }

    public WarehouseInfoEntity(Map entity) {
        super(entity);
    }
	
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
	
	public String getStatus() {
	    return getString("code");
	}
	public void setStatus(String status) {
	    set("status", status);
	}
	
	
}
