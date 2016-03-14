package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/** 
 * WeightInfoEntity
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月18日 上午9:50:04 
 * @email 1129290218@qq.com
 * @description  重量信息
 */
public class WeightInfoEntity extends Pojo{
	//重量
	private int weight;

	public int getWeight() {
		return getInt("weight");
	}

	public void setWeight(int weight) {
		set("weight", weight);
	}
	
}
