package com.douzone.mysite.vo;

public class GalleryVo {
	private Long no;
	private String coments;
	private String url;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getComents() {
		return coments;
	}
	public void setComents(String coments) {
		this.coments = coments;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", coments=" + coments + ", url=" + url + "]";
	}
	
	
}
