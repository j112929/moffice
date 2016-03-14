package com.plocc.moffice.entity;

import java.util.List;

import com.plocc.framework.entity.Pojo;
import com.plocc.framework.support.JsonpHelper;

/** 
 * SalesDeliveryEntity
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月17日 下午7:33:01 
 * @email 1129290218@qq.com
 * @description  销售发货信息
 */
public class SalesDeliveryEntity extends Pojo{
	//扩展字段
	private Object customFields;
	//readonly 文档编号
	private String docNumber;
	//readonly 发货状态   OPEN
	//	CLOSED
	//	PICK
	//	PACK
	//	SHIPPING
	//	PENDING 不确定的
	//	CANCELLED
	private String status;
	//客户信息
	private CustomerInfoEntity customerInfoEntity;
	//仓库信息
	private WarehouseInfoEntity warehouseInfoEntity;
	//销售订单数
	private String salesOrderNumber;
	//地址信息
	private AddressInfoEntity addressInfoEntity; 
	//创建时间
	private String creationTime;
	//更新时间"2015-09-22T06:10:00.000Z"
	private String updateTime;
	//送货时间
	private String shippingTime;
	//最早送到时间
	private String earliestShippingTime;
	//最晚送到时间
	private String latestShippingTime;
	//最早发货时间
	private String earliestDeliveryTime;
	//最晚发货时间
	private String latestDeliveryTime;
	//航空公司信息
	private CarrierInfoEntity carrierInfoEntity;
	//追踪号码
	private String trackingNumber;
	//重量信息
	private WeightInfoEntity weightInfoEntity; 
	//包裹大小信息
	private PackageSizeInfoEntity packageSizeInfoEntity;
	//备注
	private String remark;
	//lines	required 线路
	private Object[]  lines;
	public Object getCustomFields() {
		return get("customFields");
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
	public String getStatus() {
		return getString("status");
	}
	public void setStatus(String status) {
		set("status", status);
	}
	public CustomerInfoEntity getCustomerInfo() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("customer")),CustomerInfoEntity.class);
	}
	public void setCustomerInfo(CustomerInfoEntity customerInfoEntity) {
		set("customer", customerInfoEntity);
	}
	public WarehouseInfoEntity getWarehouseInfo() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("warehouse")),WarehouseInfoEntity.class);
	}
	public void setWarehouseInfo(WarehouseInfoEntity warehouseInfoEntity) {
		set("warehouse", warehouseInfoEntity);
	}
	public String getSalesOrderNumber() {
		return getString("salesOrderNumber");
	}
	public void setSalesOrderNumber(String salesOrderNumber) {
		set("salesOrderNumber", salesOrderNumber);
	}
	public AddressInfoEntity getAddressInfo() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("shippingAddress")),AddressInfoEntity.class);
	}
	public void setAddressInfo(AddressInfoEntity addressInfoEntity) {
		set("shippingAddress", addressInfoEntity);
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
	public String getShippingTime() {
		return getString("shippingTime");
	}
	public void setShippingTime(String shippingTime) {
		set("shippingTime", shippingTime);
	}
	public String getEarliestShippingTime() {
		return getString("earliestShippingTime");
	}
	public void setEarliestShippingTime(String earliestShippingTime) {
		set("earliestShippingTime", earliestShippingTime);
	}
	public String getLatestShippingTime() {
		return getString("latestShippingTime");
	}
	public void setLatestShippingTime(String latestShippingTime) {
		set("latestShippingTime", latestShippingTime);
	}
	public String getEarliestDeliveryTime() {
		return getString("earliestDeliveryTime");
	}
	public void setEarliestDeliveryTime(String earliestDeliveryTime) {
		set("earliestDeliveryTime", earliestDeliveryTime);
	}
	public String getLatestDeliveryTime() {
		return getString("latestDeliveryTime");
	}
	public void setLatestDeliveryTime(String latestDeliveryTime) {
		set("latestDeliveryTime", latestDeliveryTime);
	}
	public CarrierInfoEntity getCarrierInfo() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("carrier")),CarrierInfoEntity.class);
	}
	public void setCarrierInfo(CarrierInfoEntity carrierInfoEntity) {
		set("carrier", carrierInfoEntity);
	}
	public String getTrackingNumber() {
		return getString("trackingNumber");
	}
	public void setTrackingNumber(String trackingNumber) {
		set("trackingNumber", trackingNumber);
	}
	public WeightInfoEntity getWeightInfo() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("weight")),WeightInfoEntity.class);
	}
	public void setWeightInfo(WeightInfoEntity weightInfoEntity) {
		set("weight", weightInfoEntity);
	}
	public PackageSizeInfoEntity getPackageSizeInfo() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("packageSize")),PackageSizeInfoEntity.class);
	}
	public void setPackageSizeInfo(PackageSizeInfoEntity packageSizeInfoEntity) {
		set("packageSize", packageSizeInfoEntity);
	}
	public String getRemark() {
		return getString("remark");
	}
	public void setRemark(String remark) {
		set("remark", remark);
	}
	public List getLines() {
		return (List) get("lines");
	}
	public void setLines(List lines) {
		set("lines", lines);
	}
	
}
