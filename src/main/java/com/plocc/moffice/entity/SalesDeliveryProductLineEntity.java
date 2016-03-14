package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/** 
 * SalesOrderProductLineEntity
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月22日 下午4:57:29 
 * @email 1129290218@qq.com
 * @description  订单产品线
 */
public class SalesDeliveryProductLineEntity extends Pojo{
	private int deliveryQuantity;
	private String calculationBase;
	private BaseDocumentInfo baseDocument;
	
	public String getCalculationBase() {
		return getString("calculationBase");
	}
	public void setCalculationBase(String calculationBase) {
		set("calculationBase", calculationBase);
	}
	public int getDeliveryQuantity() {
		return getInt("deliveryQuantity");
	}
	public void setDeliveryQuantity(int deliveryQuantity) {
		set("deliveryQuantity", deliveryQuantity);
	}
	public BaseDocumentInfo getBaseDocument() {
		return (BaseDocumentInfo) get("baseDocument");
	}
	public void setBaseDocument(BaseDocumentInfo baseDocument) {
		set("baseDocument", baseDocument);
	}
	
	
	
}
