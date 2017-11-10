package com.taotao.manage.vo;

import java.io.Serializable;

public class PicUploadResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8588294720143233189L;
	
	private Integer error;
	private String url;
	private String height;
	private String width;
	@Override
	public String toString() {
		return "PicUploadResult [error=" + error + ", url=" + url + ", height=" + height + ", width=" + width + "]";
	}
	public Integer getError() {
		return error;
	}
	public void setError(Integer error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	
	
	
}
