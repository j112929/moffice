package com.plocc.moffice.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.entity.Pojo;
import com.plocc.framework.support.JsonpHelper;

@SuppressWarnings({"unused", "rawtypes"})
public class SKUAllWarehouseInfoEntity extends Pojo {
    
    private static final long serialVersionUID = 1L;
    
    private BigDecimal inStockTotal;
    private BigDecimal orderedTotal;
    private BigDecimal committedTotal;
    private List<SKUWarehouseInfoEntity> warehouseInfoList;
    
    public SKUAllWarehouseInfoEntity() {
    }

    public SKUAllWarehouseInfoEntity(Map entity) {
        super(entity);
    }
    
    /**
     * @return the inStockTotal
     */
    public BigDecimal getInStockTotal() {
        return getBigDecimal("inStockTotal");
    }
    /**
     * @param inStockTotal the inStockTotal to set
     */
    public void setInStockTotal(BigDecimal inStockTotal) {
        set("inStockTotal", inStockTotal);
    }
    /**
     * @return the orderedTotal
     */
    public BigDecimal getOrderedTotal() {
        return getBigDecimal("orderedTotal");
    }
    /**
     * @param orderedTotal the orderedTotal to set
     */
    public void setOrderedTotal(BigDecimal orderedTotal) {
        set("orderedTotal", orderedTotal);
    }
    /**
     * @return the committedTotal
     */
    public BigDecimal getCommittedTotal() {
        return getBigDecimal("committedTotal");
    }
    /**
     * @param committedTotal the committedTotal to set
     */
    public void setCommittedTotal(BigDecimal committedTotal) {
        set("committedTotal", committedTotal);
    }
    /**
     * @return the warehouseInfoList
     */
    public List<SKUWarehouseInfoEntity> getWarehouseInfoList() {
        List<SKUWarehouseInfoEntity> list = JsonpHelper.toObject(JsonpHelper.toString(get("warehouseInfoList")), new TypeReference<List<SKUWarehouseInfoEntity>>() {
        });
        return list;
    }
    /**
     * @param warehouseInfoList the warehouseInfoList to set
     */
    public void setWarehouseInfoList(List<SKUWarehouseInfoEntity> warehouseInfoList) {
        set("warehouseInfoList", warehouseInfoList);
    }
    
}
