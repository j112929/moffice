package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/** 
 *	AmountInfoEntity
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月18日 上午9:49:36 
 * @email 1129290218@qq.com
 * @description  数量信息
 */
public class AmountInfoEntity extends Pojo{
	//数量
	private int amount;
	//当地货币数量
	private int amountLocalCurrency;
	public int getAmount() {
		return getInt(amount);
	}
	public void setAmount(int amount) {
		set("amount", amount);
	}
	public int getAmountLocalCurrency() {
		return getInt(amountLocalCurrency);
	}
	public void setAmountLocalCurrency(int amountLocalCurrency) {
		set("amountLocalCurrency", amountLocalCurrency);
	}
	
}
