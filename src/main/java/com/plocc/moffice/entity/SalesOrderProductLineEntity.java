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
public class SalesOrderProductLineEntity extends Pojo{
	private String lineNumber;
	private int quantity;
	private String calculationBase;
	
	
	public String getLineNumber() {
		return getString("lineNumber");
	}
	public void setLineNumber(String lineNumber) {
		set("lineNumber", lineNumber);
	}
	public int getQuantity() {
		return getInt("quantity");
	}
	public void setQuantity(int quantity) {
		set("quantity", quantity);
	}
	public String getCalculationBase() {
		return getString("calculationBase");
	}
	public void setCalculationBase(String calculationBase) {
		set("calculationBase", calculationBase);
	}
	
	
	
}
