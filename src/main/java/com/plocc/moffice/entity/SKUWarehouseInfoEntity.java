package com.plocc.moffice.entity;

import java.math.BigDecimal;
import java.util.Map;

import com.plocc.framework.entity.Pojo;

@SuppressWarnings({"unused", "rawtypes"})
public class SKUWarehouseInfoEntity extends Pojo {

    private static final long serialVersionUID = 1L;
    
    private Integer warehouseId;
    private String warehouseCode;
    private String warehouseName;
    private BigDecimal inStock;
    private BigDecimal ordered;
    private BigDecimal committed;
    private BigDecimal minInventory;
    private BigDecimal reOrderQuantity;
    private BigDecimal ats;
    private String remark;
    
    public SKUWarehouseInfoEntity() {
    }

    public SKUWarehouseInfoEntity(Map entity) {
        super(entity);
    }
    
    /**
     * @return the warehouseId
     */
    public Integer getWarehouseId() {
        return getInteger("warehouseId");
    }
    /**
     * @param warehouseId the warehouseId to set
     */
    public void setWarehouseId(Integer warehouseId) {
        set("warehouseId", warehouseId);
    }
    /**
     * @return the warehouseCode
     */
    public String getWarehouseCode() {
        return getString("warehouseCode");
    }
    /**
     * @param warehouseCode the warehouseCode to set
     */
    public void setWarehouseCode(Integer warehouseCode) {
        set("warehouseCode", warehouseCode);
    }
    /**
     * @return the warehouseName
     */
    public String getWarehouseName() {
        return getString("warehouseName");
    }
    /**
     * @param warehouseName the warehouseName to set
     */
    public void setWarehouseName(Integer warehouseName) {
        set("warehouseName", warehouseName);
    }
    /**
     * @return the inStock
     */
    public BigDecimal getInStock() {
        return getBigDecimal("inStock");
    }
    /**
     * @param inStock the inStock to set
     */
    public void setInStock(BigDecimal inStock) {
        set("inStock", inStock);
    }
    /**
     * @return the ordered
     */
    public BigDecimal getOrdered() {
        return getBigDecimal("ordered");
    }
    /**
     * @param ordered the ordered to set
     */
    public void setOrdered(BigDecimal ordered) {
        set("ordered", ordered);
    }
    /**
     * @return the committed
     */
    public BigDecimal getCommitted() {
        return getBigDecimal("committed");
    }
    /**
     * @param committed the committed to set
     */
    public void setCommitted(BigDecimal committed) {
        set("committed", committed);
    }
    /**
     * @return the minInventory
     */
    public BigDecimal getMinInventory() {
        return getBigDecimal("minInventory");
    }
    /**
     * @param minInventory the minInventory to set
     */
    public void setMinInventory(BigDecimal minInventory) {
        set("minInventory", minInventory);
    }
    /**
     * @return the reOrderQuantity
     */
    public BigDecimal getReOrderQuantity() {
        return getBigDecimal("reOrderQuantity");
    }
    /**
     * @param reOrderQuantity the reOrderQuantity to set
     */
    public void setReOrderQuantity(BigDecimal reOrderQuantity) {
        set("reOrderQuantity", reOrderQuantity);
    }
    /**
     * @return the ats
     */
    public BigDecimal getAts() {
        return getBigDecimal("ats");
    }
    /**
     * @param ats the ats to set
     */
    public void setAts(BigDecimal ats) {
        set("ats", ats);
    }
    /**
     * @return the remark
     */
    public String getRemark() {
        return getString("remark");
    }
    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        set("remark", remark);
    }
    
}
