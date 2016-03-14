/**
 * 宝龙电商
 * com.plocc.moffice.entity
 * SKUInfoEntity.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.entity.Pojo;
import com.plocc.framework.support.JsonpHelper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 属性类型实体
 * 
 * @author Administrator
 *
 */
@SuppressWarnings({"unused", "rawtypes"})
public class VariantInfoEntity extends Pojo {
    
    private static final long serialVersionUID = 1L;
    
    // 属性类型名称
	private String name;
	// 属性类型描述
	private String description;
	// 显示排序
	private Integer displayOrder;
	// 创建时间
	private String creationTime;
	// 修改时间
	private String updateTime;
	// 属性类型值
	private List<VariantValueInfoEntity> variantValues;
	
	
    public VariantInfoEntity() {
    }

    public VariantInfoEntity(Map entity) {
        super(entity);
    }

    /**
     * @return the name
     */
    public String getName() {
        return getString("name");
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        set("name", name);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return getString("description");
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        set("description", description);
    }

    /**
     * @return the displayOrder
     */
    public Integer getDisplayOrder() {
        return getInteger("displayOrder");
    }

    /**
     * @param displayOrder the displayOrder to set
     */
    public void setDisplayOrder(Integer displayOrder) {
        set("displayOrder", displayOrder);
    }

    /**
     * @return the creationTime
     */
    public String getCreationTime() {
        return getString("creationTime");
    }

    /**
     * @param creationTime the creationTime to set
     */
    public void setCreationTime(String creationTime) {
        set("creationTime", creationTime);
    }

    /**
     * @return the updateTime
     */
    public String getUpdateTime() {
        return getString("updateTime");
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(String updateTime) {
        set("updateTime", updateTime);
    }

    /**
     * @return the variantValues
     */
    public List<VariantValueInfoEntity> getVariantValues() {
        List<VariantValueInfoEntity> list = JsonpHelper.toObject(JsonpHelper.toString(get("variantValues")), new TypeReference<List<VariantValueInfoEntity>>() {
        });
        return list;
    }

    /**
     * @param variantValues the variantValues to set
     */
    public void setVariantValues(List<VariantValueInfoEntity> variantValues) {
        set("variantValues", variantValues);
    }
	
}
