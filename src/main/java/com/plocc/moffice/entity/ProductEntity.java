/**
 * 宝龙电商
 * com.plocc.moffice.entity
 * ProductEntity.java
 * <p/>
 * 2016-02-17
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.entity;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.support.JsonpHelper;

import org.hibernate.annotations.ColumnDefault;

import com.plocc.framework.entity.Pojo;

/**
 * ProductEntity
 * @author chenyong
 * @version 1.0.0
 * @time 15:43
 * @email chenyong2@powerlong.com
 * @description 产品信息
 */
@SuppressWarnings({ "unused", "serial", "rawtypes" })
public class ProductEntity extends Pojo{
    
    private Object customFields;
    //产品编号
    private String code;
    //产品名称
    private String name;
    //产品类型
    private String type;
    //产品类别
    private CategoryInfoEntity category;
    
    private String batchSerial;
    //产品保质期
    private String shelfLife;
    //产品失效前的警告天数
    private Integer alertDaysBeforeExpiry;
    //产品描述信息
    private ProductTaxInfoEntity productTaxClass;
    //产品状态
    private String status;
    //产品条形码
    private String barCode;
    private Boolean backOrderAllowed;
    private Boolean dropShipAllowed;
    //产品制造商
    private ManufacturerInfoEntity manufacturer;
    //产品品牌
    private BrandInfoEntity brand;
    //产品供应商
    private VendorInfoEntity vendor;
    private Boolean variantEnabled;
    //是否显示库存
    private Boolean inventoryEnabled;
    //是否上传产品图片
    private Boolean imageUploaded;
    //产品附件信息
    private AttachmentEntity mainImage;
    //产品计量单位信息
    private UomGroupInfoEntity uomGroup;
    
    private List<SKUInfoEntity> skus;
    //产品创建日期
    private String creationTime;
    //产品更新日期
    private String updateTime;
    
    
    
    public ProductEntity() {
    }

    public ProductEntity(Map entity) {
        super(entity);
    }
    
	public String getCode() {
		return getString("code");
	}
	public void setCode(String code) {
		set("code", code);
	}
	public String getName() {
		return getString("name");
	}
	public void setName(String name) {
		set("name", name);
	}
	public String getType() {
		return getString("type");
	}
	public void setType(String type) {
		set("type", type);
	}
	public CategoryInfoEntity getCategory() {
	    return JsonpHelper.toObject(JsonpHelper.toString(this.get("category")), CategoryInfoEntity.class);
	}
	public void setCategory(CategoryInfoEntity category) {
		set("category", category);
	}
	public String getBatchSerial() {
		return getString("batchSerial");
	}
	public void setBatchSerial(String batchSerial) {
		set("batchSerial", batchSerial);
	}
	public String getShelfLife() {
		return getString("shelfLife");
	}
	public void setShelfLife(String shelfLife) {
		set("shelfLife", shelfLife);
	}
	public Integer getAlertDaysBeforeExpiry() {
		return getInteger("alertDaysBeforeExpiry");
	}
	public void setAlertDaysBeforeExpiry(Integer alertDaysBeforeExpiry) {
		set("alertDaysBeforeExpiry", alertDaysBeforeExpiry);
	}
	public ProductTaxInfoEntity getProductTaxClass() {
	    return JsonpHelper.toObject(JsonpHelper.toString(this.get("productTaxClass")), ProductTaxInfoEntity.class);
	}
	public void setProductTaxClass(ProductTaxInfoEntity productTaxClass) {
		set("productTaxClass", productTaxClass);
	}
	
	public String getStatus() {
		return getString("status");
	}
	public void setStatus(String status) {
		set("status", status);
	}
	public String getBarCode() {
		return getString("barCode");
	}
	public void setBarCode(String barCode) {
		set("barCode", barCode);
	}
	public Boolean getBackOrderAllowed() {
		return getBoolean("backOrderAllowed");
	}
	public void setBackOrderAllowed(Boolean backOrderAllowed) {
		set("backOrderAllowed", backOrderAllowed);
	}
	public Boolean getDropShipAllowed() {
		return getBoolean("dropShipAllowed");
	}
	public void setDropShipAllowed(Boolean dropShipAllowed) {
		set("dropShipAllowed", dropShipAllowed);
	}
	public ManufacturerInfoEntity getManufacturer() {
	    return JsonpHelper.toObject(JsonpHelper.toString(this.get("manufacturer")), ManufacturerInfoEntity.class);
	}
	public void setManufacturer(ManufacturerInfoEntity manufacturer) {
		set("manufacturer", manufacturer);
	}
	public BrandInfoEntity getBrand() {
	    return JsonpHelper.toObject(JsonpHelper.toString(this.get("brand")), BrandInfoEntity.class);
	}
	public void setBrand(BrandInfoEntity brand) {
		set("brand", brand);
	}
	public VendorInfoEntity getVendor() {
		return JsonpHelper.toObject(JsonpHelper.toString(this.get("vendor")), VendorInfoEntity.class);
	}
	public void setVendor(VendorInfoEntity vendor) {
		set("vendor", vendor);
	}
	public Boolean getVariantEnabled() {
		return getBoolean("variantEnabled");
	}
	public void setVariantEnabled(Boolean variantEnabled) {
		set("variantEnabled", variantEnabled);
	}
	public Boolean getInventoryEnabled() {
		return getBoolean("inventoryEnabled");
	}
	public void setInventoryEnabled(Boolean inventoryEnabled) {
		set("inventoryEnabled", inventoryEnabled);
	}
	public Boolean getImageUploaded() {
		return getBoolean("imageUploaded");
	}
	public void setImageUploaded(Boolean imageUploaded) {
		set("imageUploaded", imageUploaded);
	}
	public AttachmentEntity getMainImage() {
	    return JsonpHelper.toObject(JsonpHelper.toString(this.get("mainImage")), AttachmentEntity.class);
	}
	public void setMainImage(AttachmentEntity mainImage) {
		set("mainImage", mainImage);
	}
	public UomGroupInfoEntity getUomGroup() {
	    return JsonpHelper.toObject(JsonpHelper.toString(this.get("uomGroup")), UomGroupInfoEntity.class);
	}
	public void setUomGroup(UomGroupInfoEntity uomGroup) {
		set("uomGroup", uomGroup);
	}
	public List<SKUInfoEntity> getSkus() {
	    List<SKUInfoEntity> list = JsonpHelper.toObject(JsonpHelper.toString(this.get("skus")), new TypeReference<List<SKUInfoEntity>>() {
        });
		return list;
	}
	public void setSkus(List<SKUInfoEntity> skus) {
		set("skus", skus);
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
	public Object getCustomFields() {
		return get("customFields");
	}
	public void setCustomFields(Object customFields) {
		set("customFields", customFields);
	}
	
}
