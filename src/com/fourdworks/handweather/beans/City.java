package com.fourdworks.handweather.beans;

/**
 * 功能:城市的javaBean 作者:mike 时间：2015-10-28 上午10:02:57 修改:
 */
public class City {
	private String _id;// 城市id INT
	private String province;// 省名字 VARCHAR (200)
	private String city;// 市名字 VARCHAR (200)
	private String allpy;// 市名全拼 VARCHAR (100)
	private String number;// 邮编 VARCHAR (100)
	private String allfirstpy;// 市名首拼 VARCHAR (100)
	private String firstpy;// 市名首字母 VARCHAR (100)
	
	public City(String _id, String province, String city, String allpy,
			String number, String allfirstpy, String firstpy) {
		super();
		this._id = _id;
		this.province = province;
		this.city = city;
		this.allpy = allpy;
		this.number = number;
		this.allfirstpy = allfirstpy;
		this.firstpy = firstpy;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAllpy() {
		return allpy;
	}

	public void setAllpy(String allpy) {
		this.allpy = allpy;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAllfirstpy() {
		return allfirstpy;
	}

	public void setAllfirstpy(String allfirstpy) {
		this.allfirstpy = allfirstpy;
	}

	public String getFirstpy() {
		return firstpy;
	}

	public void setFirstpy(String firstpy) {
		this.firstpy = firstpy;
	}

	@Override
	public String toString() {
		return "City [_id=" + _id + ", province=" + province + ", city=" + city
				+ ", allpy=" + allpy + ", number=" + number + ", allfirstpy="
				+ allfirstpy + ", firstpy=" + firstpy + "]";
	}
	
	
	
}
