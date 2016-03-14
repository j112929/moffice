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
 * SKUInfoEntity
 * @author chenyong
 * @version 1.0.0
 * @time 13:45
 * @email chenyong2@powerlong.com
 * @description 产品品牌
 */
@SuppressWarnings({"unused", "rawtypes"})
public class SKUInfoEntity extends Pojo {
    
    private static final long serialVersionUID = 1L;
    
    // SKU名称
	private String name;
	// SKU编码
	private String code;
	// 条形码
	private String barCode;
	// 状态： Active Inactive
	private String status;
	// netPurchasePrice
	private BigDecimal netPurchasePrice;
	// SKU编码
	private BigDecimal grossPurchasePrice;
	// 重量
	private BigDecimal weight;
	// 长度
	private BigDecimal length;
	// 高度
	private BigDecimal height;
	// 宽度
	private BigDecimal width;
	// 模型
	private String model;
	// 所属产品
	private ProductEntity product;
	// SKU 属性值
	private List<VariantValueInfoEntity> relatedVariantValues;
	
	/****************重新封装的值，需要再次查询才能赋值，主要用于前台显示***************/
	// 标准不含税价
    private BigDecimal netStandardPrice;
    // 标准含税价
    private BigDecimal grossStandardPrice;
    // 库存
    private BigDecimal inStockQuantity;
    
	
	
    public SKUInfoEntity() {
    }

    public SKUInfoEntity(Map entity) {
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

    /**
     * @return the barCode
     */
    public String getBarCode() {
        return getString("barCode");
    }

    /**
     * @param barCode the barCode to set
     */
    public void setBarCode(String barCode) {
        set("barCode", barCode);
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return getString("status");
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        set("status", status);
    }

    /**
     * @return the netPurchasePrice
     */
    public BigDecimal getNetPurchasePrice() {
        return getBigDecimal("netPurchasePrice");
    }

    /**
     * @param netPurchasePrice the netPurchasePrice to set
     */
    public void setNetPurchasePrice(BigDecimal netPurchasePrice) {
        set("netPurchasePrice", netPurchasePrice);
    }

    /**
     * @return the grossPurchasePrice
     */
    public BigDecimal getGrossPurchasePrice() {
        return getBigDecimal("grossPurchasePrice");
    }

    /**
     * @param grossPurchasePrice the grossPurchasePrice to set
     */
    public void setGrossPurchasePrice(BigDecimal grossPurchasePrice) {
        set("grossPurchasePrice", grossPurchasePrice);
    }

    /**
     * @return the weight
     */
    public BigDecimal getWeight() {
        return getBigDecimal("weight");
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(BigDecimal weight) {
        set("weight", weight);
    }

    /**
     * @return the length
     */
    public BigDecimal getLength() {
        return getBigDecimal("length");
    }

    /**
     * @param length the length to set
     */
    public void setLength(BigDecimal length) {
        set("length", length);
    }

    /**
     * @return the height
     */
    public BigDecimal getHeight() {
        return getBigDecimal("height");
    }

    /**
     * @param height the height to set
     */
    public void setHeight(BigDecimal height) {
        set("height", height);
    }

    /**
     * @return the width
     */
    public BigDecimal getWidth() {
        return getBigDecimal("width");
    }

    /**
     * @param width the width to set
     */
    public void setWidth(BigDecimal width) {
        set("width", width);
    }

    /**
     * @return the model
     */
    public String getModel() {
        return getString("model");
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        set("model", model);
    }

    /**
     * @return the product
     */
    public ProductEntity getProduct() {
        return JsonpHelper.toObject(JsonpHelper.toString(get("product")), ProductEntity.class);
    }

    /**
     * @param product the product to set
     */
    public void setProduct(ProductEntity product) {
        set("product", product);
    }

    /**
     * @return the relatedVariantValues
     */
    public List<VariantValueInfoEntity> getRelatedVariantValues() {
        List<VariantValueInfoEntity> list = JsonpHelper.toObject(JsonpHelper.toString(get("relatedVariantValues")), new TypeReference<List<VariantValueInfoEntity>>() {
        });
        return list;
    }

    /**
     * @param relatedVariantValues the relatedVariantValues to set
     */
    public void setRelatedVariantValues(List<VariantValueInfoEntity> relatedVariantValues) {
        set("relatedVariantValues", relatedVariantValues);
    }

    /**
     * @return the netStandardPrice
     */
    public BigDecimal getNetStandardPrice() {
        return getBigDecimal("netStandardPrice");
    }

    /**
     * @param netStandardPrice the netStandardPrice to set
     */
    public void setNetStandardPrice(BigDecimal netStandardPrice) {
        set("netStandardPrice", netStandardPrice);
    }

    /**
     * @return the grossStandardPrice
     */
    public BigDecimal getGrossStandardPrice() {
        return getBigDecimal("grossStandardPrice");
    }

    /**
     * @param grossStandardPrice the grossStandardPrice to set
     */
    public void setGrossStandardPrice(BigDecimal grossStandardPrice) {
        set("grossStandardPrice", grossStandardPrice);
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
	
}
