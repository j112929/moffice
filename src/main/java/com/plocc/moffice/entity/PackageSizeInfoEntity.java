package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/** 
 *	PackageSizeInfoEntity
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月18日 上午9:50:09 
 * @email 1129290218@qq.com
 * @description  包裹大小信息
 */
public class PackageSizeInfoEntity extends Pojo{
	//包裹长度
	private int length;
	//包裹宽度
	private int width;
	//包裹高度
	private int height;
	public int getLength() {
		return getInt("length");
	}
	public void setLength(int length) {
		set("length", length);
	}
	public int getWidth() {
		return getInt("width");
	}
	public void setWidth(int width) {
		set("width", width);
	}
	public int getHeight() {
		return getInt("height");
	}
	public void setHeight(int height) {
		set("height", height);
	}
	
}
