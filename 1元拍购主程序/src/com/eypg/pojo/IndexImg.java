package com.eypg.pojo;

/**
 * IndexImg entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class IndexImg implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String proUrl;
	private String proImg;
	private Integer status;

	// Constructors

	/** default constructor */
	public IndexImg() {
	}

	/** full constructor */
	public IndexImg(String title, String proUrl, String proImg,Integer status) {
		this.title = title;
		this.proUrl = proUrl;
		this.proImg = proImg;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProUrl() {
		return this.proUrl;
	}

	public void setProUrl(String proUrl) {
		this.proUrl = proUrl;
	}

	public String getProImg() {
		return this.proImg;
	}

	public void setProImg(String proImg) {
		this.proImg = proImg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	

}