package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/** 
 * ExternalMerchantInfoEntity
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月18日 上午9:48:53 
 * @email 1129290218@qq.com
 * @description  外部商业信息
 */
public class ExternalMerchantInfoEntity extends Pojo{
	//航运服务级别
	private String shipServiceLevel;
	//最早航运时间
	private String earliestShipTime;
	//最早发货时间
	private String earliestDeliveryTime;
	//商业执行id
	private String merchantFulfillmentID;
	//托运人追踪号码
	private String shipperTrackingNumber;
	public String getShipServiceLevel() {
		return getString("shipServiceLevel");
	}
	public void setShipServiceLevel(String shipServiceLevel) {
		set("shipServiceLevel", shipServiceLevel);
	}
	public String getEarliestShipTime() {
		return getString("earliestShipTime");
	}
	public void setEarliestShipTime(String earliestShipTime) {
		set("earliestShipTime", earliestShipTime);
	}
	public String getEarliestDeliveryTime() {
		return getString("earliestDeliveryTime");
	}
	public void setEarliestDeliveryTime(String earliestDeliveryTime) {
		set("earliestDeliveryTime", earliestDeliveryTime);
	}
	public String getMerchantFulfillmentID() {
		return getString("merchantFulfillmentID");
	}
	public void setMerchantFulfillmentID(String merchantFulfillmentID) {
		set("merchantFulfillmentID", merchantFulfillmentID);
	}
	public String getShipperTrackingNumber() {
		return getString("shipperTrackingNumber");
	}
	public void setShipperTrackingNumber(String shipperTrackingNumber) {
		set("shipperTrackingNumber", shipperTrackingNumber);
	}
	
}	
