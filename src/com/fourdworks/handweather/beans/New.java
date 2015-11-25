package com.fourdworks.handweather.beans;

import java.io.Serializable;

/**
 * 功能:新闻推荐javaBean
 * 作者:mike
 * 时间：2015-11-20 上午9:56:25
 * 修改:
 */
public class New implements Serializable{
	private static final long serialVersionUID = -8715184308582614938L;
	private String time;//2015-11-19 18:49private String ,
    private String title;//杨波：执著的助学者（速写）private String ,
    private String description;//杨波：执著的助学者（速写）...private String ,
    private String picUrl;//private String ,
    private String url;//http://news.sohu.com/20151119/n427138925.shtmlprivate String
    
	public New() {
		super();
	}
	
	public New(String time, String title, String description, String picUrl,
			String url) {
		super();
		this.time = time;
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
		this.url = url;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "NewsTJ [time=" + time + ", title=" + title + ", description="
				+ description + ", picUrl=" + picUrl + ", url=" + url + "]";
	}
    
    
    
    
}
