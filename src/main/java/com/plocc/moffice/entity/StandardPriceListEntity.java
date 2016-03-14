/**
 * 宝龙电商
 * com.plocc.moffice.entity
 * StandardPriceListEntity.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.entity;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.entity.Pojo;
import com.plocc.framework.support.JsonpHelper;

/**
 * StandardPriceListEntity
 * @author chenyong
 * @version 1.0.0
 * @time 13:17
 * @email chenyong2@powerlong.com
 * @description 价格列表
 */
@SuppressWarnings({"unused", "rawtypes"})
public class StandardPriceListEntity extends Pojo {
    
    private static final long serialVersionUID = 1L;
    
    // 价格列表名称
	private String name;
	// 有效时间
	private String validFrom;
	// 失效时间
	private String validTo;
	// 是否标准价格
	private List<PriceListCurrencyAndPriceMethodInfoEntity> priceOption;
	// SKU价格
	private List<SKUStandardPriceInfoEntity> skuPrices;
	
    public StandardPriceListEntity() {
    }

    public StandardPriceListEntity(Map entity) {
        super(entity);
    }

    
    /**
     * @return the skuPrices
     */
    public List<SKUStandardPriceInfoEntity> getSkuPrices() {
        List<SKUStandardPriceInfoEntity> list = JsonpHelper.toObject(JsonpHelper.toString(this.get("skuPrices")), new TypeReference<List<SKUStandardPriceInfoEntity>>() {
        });
        return list;
    }

    /**
     * @param skuPrices the skuPrices to set
     */
    public void setSkuPrices(List<SKUStandardPriceInfoEntity> skuPrices) {
        set("skuPrices", skuPrices);
    }

	
	public String getName() {
		return getString("name");
	}

	public void setName(String name) {
		set("name", name);
	}

	public String getValidFrom() {
		return getString("validFrom");
	}

    public List<PriceListCurrencyAndPriceMethodInfoEntity> getPriceOption() {
        List<PriceListCurrencyAndPriceMethodInfoEntity> list = JsonpHelper.toObject(JsonpHelper.toString(this.get("priceOption")), new TypeReference<List<PriceListCurrencyAndPriceMethodInfoEntity>>() {
        });
		return list;
	}

	public void setPriceOption(List<PriceListCurrencyAndPriceMethodInfoEntity> priceOption) {
		set("priceOption", priceOption);
	}

	public void setValidFrom(String validFrom) {
		set("validFrom", validFrom);
	}

	public String getValidTo() {
		return getString("validTo");
	}

	public void setValidTo(String validTo) {
		set("validTo", validTo);
	}

	public String getCreationTime() {
		return getString("creationTime");
	}

	public void setCreationTime(String creationTime) {
		set("creationTime", creationTime);
	}

	public String getUpdateTime() {
		return getString("updateTime");
	}

	public void setUpdateTime(String updateTime) {
		set("updateTime", updateTime);
	}
	
	
	
}
