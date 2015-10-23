package com.fourdworks.handweather.beans;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8810432702193442453L;
	
	private String userName;
	private int age;
	private String loves;
	
	public User(String userName, int age, String loves) {
		super();
		this.userName = userName;
		this.age = age;
		this.loves = loves;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getLoves() {
		return loves;
	}
	public void setLoves(String loves) {
		this.loves = loves;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", age=" + age + ", loves="
				+ loves + "]";
	}
	
	
}
