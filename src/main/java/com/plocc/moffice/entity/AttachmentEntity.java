/**
 * 宝龙电商
 * com.plocc.moffice.entity
 * AttachmentEntity.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.entity;

import java.util.Map;

import com.plocc.framework.entity.Pojo;

/**
 * AttachmentEntity
 * @author chenyong
 * @version 1.0.0
 * @time 11:28
 * @email chenyong2@powerlong.com
 * @description 产品附件
 */
@SuppressWarnings({ "unused", "serial", "rawtypes" })
public class AttachmentEntity extends Pojo{
	//附件名称
	private String fileName;
	//附件大小
	private Integer fileSize;
	
	private String mimeType;
	
	private Object content;
	
	private String uploadedTime;

	
    public AttachmentEntity() {
    }

    public AttachmentEntity(Map entity) {
        super(entity);
    }
	
	public String getFileName() {
		return getString("fileName");
	}

	public void setFileName(String fileName) {
		set("fileName", fileName);
	}

	public Integer getFileSize() {
		return getInteger("fileSize");
	}

	public void setFileSize(Integer fileSize) {
		set("fileSize", fileSize);
	}

	public String getMimeType() {
		return getString("mimeType");
	}

	public void setMimeType(String mimeType) {
		set("mimeType", mimeType);
	}

	public Object getContent() {
		return get("content");
	}

	public void setContent(Object content) {
		set("content", content);
	}

	public String getUploadedTime() {
		return getString("uploadedTime");
	}

	public void setUploadedTime(String uploadedTime) {
		set("uploadedTime", uploadedTime);
	}
	
	
	
}
