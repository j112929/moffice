package com.plocc.moffice.entity;

import java.math.BigDecimal;
import java.util.Map;

import com.plocc.framework.entity.Pojo;

@SuppressWarnings({"unused", "rawtypes"})
public class CurrencyPriceInfoEntity extends Pojo {
    
    private static final long serialVersionUID = 1L;
    
    private String currencyCode;
    private String priceMethod;
    private BigDecimal price;
    
    public CurrencyPriceInfoEntity() {
    }

    public CurrencyPriceInfoEntity(Map entity) {
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
     * @return the priceMethod
     */
    public String getPriceMethod() {
        return getString("priceMethod");
    }
    /**
     * @param priceMethod the priceMethod to set
     */
    public void setPriceMethod(String priceMethod) {
        set("priceMethod", priceMethod);
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
