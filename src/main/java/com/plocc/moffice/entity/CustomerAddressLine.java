package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;
import com.plocc.framework.support.JsonpHelper;

public class CustomerAddressLine extends Pojo {
	private AddressInfoEntity address;
	private boolean defaultBillTo;
	private boolean defaultShipTo;
	public AddressInfoEntity getAddress() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("address")),AddressInfoEntity.class);
	}
	public void setAddress(AddressInfoEntity address) {
		set("address", address);
	}
	public boolean isDefaultBillTo() {
		return getBoolean("defaultBillTo");
	}
	public void setDefaultBillTo(boolean defaultBillTo) {
		set("defaultBillTo", defaultBillTo);
	}
	public boolean isDefaultShipTo() {
		return getBoolean("defaultShipTo");
	}
	public void setDefaultShipTo(boolean defaultShipTo) {
		set("defaultShipTo", defaultShipTo);
	}
}
