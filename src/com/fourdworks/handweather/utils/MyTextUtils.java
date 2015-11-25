package com.fourdworks.handweather.utils;

/**
 * 功能:处理文本的工具类
 * 作者:mike
 * 时间：2015-11-18 上午9:59:31
 * 修改:
 */
public class MyTextUtils {
	/**
	 * 去掉城市中的市   武汉市--->武汉
	 * @param cityName
	 * @return
	 */
	public static String getCity(String cityName){
		return cityName.replace("市", "");
	}
}
