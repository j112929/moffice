/**
 * 宝龙电商
 * com.plocc.moffice.entity
 * SKUInfoEntity.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;
import com.plocc.framework.support.JsonpHelper;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 属性类型实体
 * 
 * @author Administrator
 *
 */
@SuppressWarnings({"unused", "rawtypes"})
public class VariantValueInfoEntity extends Pojo {
    
    private static final long serialVersionUID = 1L;
    
    // 属性类型名称
	private String value;
	// 属性类型描述
	private String code;
	// 显示排序
	private Integer displayOrder;
	// 所属类型值
	private VariantInfoEntity variant;
	// 创建时间
	private String creationTime;
	// 修改时间
	private String updateTime;
	
	
    public VariantValueInfoEntity() {
    }

    public VariantValueInfoEntity(Map entity) {
        super(entity);
    }

    /**
     * @return the value
     */
    public String getValue() {
        return getString("value");
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        set("value", value);
    }

    /**
     * @return the code
     */
    public String getCode() {
        return getString("code");
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        set("code", code);
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
     * @return the variant
     */
    public VariantInfoEntity getVariant() {
        return JsonpHelper.toObject(JsonpHelper.toString(get("variant")), VariantInfoEntity.class);
    }

    /**
     * @param variant the variant to set
     */
    public void setVariant(VariantInfoEntity variant) {
        set("variant", variant);
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
	
}
