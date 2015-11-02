package com.fourdworks.handweather.beans;

/**
 * 功能:用户城市模型 作者:mike 时间：2015-10-30 上午9:54:45 修改:
 */
public class UserCity {
	private String city;// 城市名字:武汉
	private String lowtemp;// 最低温:13℃
	private String hightemp;// 最高温:38℃
	private String weatherinfo;// 天气信息:小雨
	private String statue;// 状态:0(未选中) 1（选中：默认值）
	
	public UserCity(String city, String lowtemp, String hightemp,
			String weatherinfo, String statue) {
		super();
		this.city = city;
		this.lowtemp = lowtemp;
		this.hightemp = hightemp;
		this.weatherinfo = weatherinfo;
		this.statue = statue;
	}

	public String getCity() {
		return city;
	}
	
	/**
	 * 返回带有通配符的城市名字
	 * @return
	 */
	public String getCity_() {
		return city+"%";
	}
	
	
	public void setCity(String city) {
		this.city = city;
	}

	public String getLowtemp() {
		return lowtemp;
	}

	public void setLowtemp(String lowtemp) {
		this.lowtemp = lowtemp;
	}

	public String getHightemp() {
		return hightemp;
	}

	public void setHightemp(String hightemp) {
		this.hightemp = hightemp;
	}

	public String getWeatherinfo() {
		return weatherinfo;
	}

	public void setWeatherinfo(String weatherinfo) {
		this.weatherinfo = weatherinfo;
	}

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	@Override
	public String toString() {
		return "UserCity [city=" + city + ", lowtemp=" + lowtemp
				+ ", hightemp=" + hightemp + ", weatherinfo=" + weatherinfo
				+ ", statue=" + statue + "]";
	}
}