package com.fourdworks.handweather;

import android.app.Application;

/**
 * 功能:全局应用程序
 * 作者:mike
 * 时间：2015-10-23 上午10:08:10
 * 修改:
 */
public class MyApplaction extends Application{
	
	private static MyApplaction instance;
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//获取本类的实例
		instance = this;
	}
	
	/**
	 * 使用本类
	 * @return
	 */
	public static MyApplaction getInstance() {
		return instance;
	}
	
}
