package com.douzone.mysite.vo;

public class GalleryVo {
	private Long no;
	private String conments;
	private String url;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getConments() {
		return conments;
	}
	public void setConments(String conments) {
		this.conments = conments;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", conments=" + conments + ", url=" + url + "]";
	}
	
	
}
