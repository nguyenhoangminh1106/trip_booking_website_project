package com.travel.web.controllers.admin.post;

import java.sql.Date;

public class Post {
	private int id;
	private String postName;
	private String img_url;
	private String desc_url;
	
	public Post(int id, String postName, String img_url, String desc_url) {
		super();
		this.id = id;
		this.postName = postName;
		this.img_url = img_url;
		this.desc_url = desc_url;
	}

	public Post(String postName, String img_url, String desc_url) {
		super();
		this.postName = postName;
		this.img_url = img_url;
		this.desc_url = desc_url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getDesc_url() {
		return desc_url;
	}

	public void setDesc_url(String desc_url) {
		this.desc_url = desc_url;
	}
	
	
	
	
}
