package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;
import com.plocc.framework.support.JsonpHelper;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 
 * @author xing_biao
 * 2016年2月24日
 * @version 1.0.0
 *
 */
@SuppressWarnings({"unused", "rawtypes"})
public class InventoryCountingLineEntity extends Pojo {
    
    private static final long serialVersionUID = 1L;

    private String lineNumber;
    private String inventoryUoM;
    private SKUInfoEntity sku;
    private BigDecimal inStockQuantity;
    private boolean isCounted;
    private BigDecimal countedQuantity;
    private BigDecimal variance;
    private BigDecimal variancePercent;
    private String remark;
    private Object[] batches;
    
    public InventoryCountingLineEntity() {
    }

    public InventoryCountingLineEntity(Map entity) {
        super(entity);
    }
    
    /**
     * @return the lineNumber
     */
    public String getLineNumber() {
        return getString("lineNumber");
    }
    /**
     * @param lineNumber the lineNumber to set
     */
    public void setLineNumber(String lineNumber) {
        set("lineNumber", lineNumber);
    }
    /**
     * @return the inventoryUoM
     */
    public String getInventoryUoM() {
        return getString("inventoryUoM");
    }
    /**
     * @param inventoryUoM the inventoryUoM to set
     */
    public void setInventoryUoM(String inventoryUoM) {
        set("inventoryUoM", inventoryUoM);
    }
    /**
     * @return the sku
     */
    public SKUInfoEntity getSku() {
        return JsonpHelper.toObject(JsonpHelper.toString(this.get("sku")), SKUInfoEntity.class);
    }
    /**
     * @param sku the sku to set
     */
    public void setSku(SKUInfoEntity sku) {
        set("sku", sku);
    }
    /**
     * @return the inStockQuantity
     */
    public BigDecimal getInStockQuantity() {
        return getBigDecimal("inStockQuantity");
    }
    /**
     * @param inStockQuantity the inStockQuantity to set
     */
    public void setInStockQuantity(BigDecimal inStockQuantity) {
        set("inStockQuantity", inStockQuantity);
    }
    /**
     * @return the isCounted
     */
    public boolean isCounted() {
        return getBoolean("isCounted");
    }
    /**
     * @param isCounted the isCounted to set
     */
    public void setCounted(boolean isCounted) {
        set("isCounted", isCounted);
    }
    /**
     * @return the countedQuantity
     */
    public BigDecimal getCountedQuantity() {
        return getBigDecimal("countedQuantity");
    }
    /**
     * @param countedQuantity the countedQuantity to set
     */
    public void setCountedQuantity(BigDecimal countedQuantity) {
        set("countedQuantity", countedQuantity);
    }
    /**
     * @return the variance
     */
    public BigDecimal getVariance() {
        return getBigDecimal("variance");
    }
    /**
     * @param variance the variance to set
     */
    public void setVariance(BigDecimal variance) {
        set("variance", variance);
    }
    /**
     * @return the variancePercent
     */
    public BigDecimal getVariancePercent() {
        return getBigDecimal("variancePercent");
    }
    /**
     * @param variancePercent the variancePercent to set
     */
    public void setVariancePercent(BigDecimal variancePercent) {
        set("variancePercent", variancePercent);
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
    /**
     * @return the batches
     */
    public Object[] getBatches() {
        return (Object[]) get("batches");
    }
    /**
     * @param batches the batches to set
     */
    public void setBatches(Object[] batches) {
        set("batches", batches);
    }
    
}
