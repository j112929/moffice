package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

public class CustomerPriceListLineEntity extends Pojo {
	private Object customFields;
	private int priceListId;
	public Object getCustomFields() {
		return getString("customFields");
	}
	public void setCustomFields(Object customFields) {
		set("customFields", customFields);
	}
	public int getPriceListId() {
		return getInt("priceListId");
	}
	public void setPriceListId(int priceListId) {
		set("priceListId", priceListId);
	}
	
	
}
