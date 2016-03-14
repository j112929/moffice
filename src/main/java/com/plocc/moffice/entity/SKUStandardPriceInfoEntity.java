package com.plocc.moffice.entity;

import java.math.BigDecimal;
import java.util.Map;

import com.plocc.framework.entity.Pojo;

@SuppressWarnings({"unused", "rawtypes"})
public class SKUStandardPriceInfoEntity extends Pojo {
    
    private static final long serialVersionUID = 1L;
    
    private String currencyCode;
    // NET_PRICE GROSS_PRICE
    private String pricingMethod;
    // 
    private BigDecimal price;
    
    
    public SKUStandardPriceInfoEntity() {
    }

    public SKUStandardPriceInfoEntity(Map entity) {
        super(entity);
    }
    
    /**
     * @return the currencyCode
     */
    public String getCurrencyCode() {
        return getString("currencyCode");
    }
    /**
     * @param currencyCode the currencyCode to set
     */
    public void setCurrencyCode(String currencyCode) {
        set("currencyCode", currencyCode);
    }
    /**
     * @return the pricingMethod
     */
    public String getPricingMethod() {
        return getString("pricingMethod");
    }
    /**
     * @param pricingMethod the pricingMethod to set
     */
    public void setPricingMethod(String pricingMethod) {
        set("pricingMethod", pricingMethod);
    }
    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return getBigDecimal("price");
    }
    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        set("price", price);
    }
    
    
}   
