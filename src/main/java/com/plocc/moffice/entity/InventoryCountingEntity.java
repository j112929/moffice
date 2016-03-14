/**
 * 宝龙电商
 * com.plocc.moffice.entity
 * InventoryCountingEntity.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.entity.Pojo;
import com.plocc.framework.support.JsonpHelper;

/**
 * InventoryCountingEntity
 * 
 * @author chenyong
 * @version 1.0.0
 * @time 20:35
 * @email chenyong2@powerlong.com
 * @description 产品库存
 */
@SuppressWarnings({ "unused", "serial", "rawtypes" })
public class InventoryCountingEntity extends Pojo {
    
    private Object customFields;
    private String docNumber;
    private Integer seriesId;
    private String status;
    private WarehouseInfoEntity warehouse;
    private String countTime;
    private BigDecimal countedTotal;
    private String creationTime;
    private String createdBy;
    private String updateTime;
    private String updatedBy;
    private String remark;
    private List<InventoryCountingLineEntity> lines;
    
    
    public InventoryCountingEntity() {
    }

    public InventoryCountingEntity(Map entity) {
        super(entity);
    }
    
    public Object getCustomFields() {
        return getString("customFields");
    }

    public void setCustomFields(Object customFields) {
        set("customFields", customFields);
    }

    public String getDocNumber() {
        return getString("docNumber");
    }

    public void setDocNumber(String docNumber) {
        set("docNumber", docNumber);
    }

    public Integer getSeriesId() {
        return getInteger("seriesId");
    }

    public void setSeriesId(Integer seriesId) {
        set("seriesId", seriesId);
    }

    public String getStatus() {
        return getString("status");
    }

    public void setStatus(String status) {
        set("status", status);
    }

    public WarehouseInfoEntity getWarehouse() {
        return JsonpHelper.toObject(JsonpHelper.toString(this.get("warehouse")), WarehouseInfoEntity.class);
    }

    public void setWarehouse(WarehouseInfoEntity warehouse) {
        set("warehouse", warehouse);
    }

    public String getCountTime() {
        return getString("countTime");
    }

    public void setCountTime(String countTime) {
        set("countTime", countTime);
    }

    public BigDecimal getCountedTotal() {
        return getBigDecimal("countedTotal");
    }

    public void setCountedTotal(BigDecimal countedTotal) {
        set("countedTotal", countedTotal);
    }

    public String getCreationTime() {
        return getString("creationTime");
    }

    public void setCreationTime(String creationTime) {
        set("creationTime", creationTime);
    }

    public String getCreatedBy() {
        return getString("createdBy");
    }

    public void setCreatedBy(String createdBy) {
        set("createdBy", createdBy);
    }

    public String getUpdateTime() {
        return getString("updateTime");
    }

    public void setUpdateTime(String updateTime) {
        set("updateTime", updateTime);
    }

    public String getUpdatedBy() {
        return getString("updatedBy");
    }

    public void setUpdatedBy(String updatedBy) {
        set("updatedBy", updatedBy);
    }

    public String getRemark() {
        return getString("remark");
    }

    public void setRemark(String remark) {
        set("remark", remark);
    }

    public List<InventoryCountingLineEntity> getLines() {
        List<InventoryCountingLineEntity> list = JsonpHelper.toObject(JsonpHelper.toString(get("lines")), new TypeReference<List<InventoryCountingLineEntity>>() {
        });
        return list;
    }

    
    public void setLines(List<InventoryCountingLineEntity> lines) {
        set("lines", lines);
    }

}
