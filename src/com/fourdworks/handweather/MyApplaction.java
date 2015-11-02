package com.fourdworks.handweather;

import android.app.Application;

import com.fourdworks.handweather.db.HandWeatherDAO;
import com.fourdworks.handweather.utils.LogUtils;

/**
 * 功能:全局应用程序 作者:mike 时间：2015-10-23 上午10:08:10 修改:
 */
public class MyApplaction extends Application {
	private String TAG = "MyApplaction";

	private static MyApplaction instance;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// 获取本类的实例
		instance = this;

		// 测试

		// AllCityDao相关
		// ------------------------------------
		// 测试单例
		// AllCityDao.getInstance();
		// 测试获取所有城市
		// Log.d(TAG, AllCityDao.getInstance().getAllCity().toString());
		// 测试搜索城市
		// Log.d(TAG, AllCityDao.getInstance().searchCity("张").toString());

		// HandWeather数据库----用户表
		// ------------------------------------
		// 1.测试添加用户城市方法
		// UserCity whs = new UserCity("武汉市", "7℃", "15℃", "小雨", "1");
		// UserCity bjs = new UserCity("北京市", "4℃", "12℃", "阴天", "1");
		// HandWeatherDAO.getInstance().addUserCity(whs);
		// HandWeatherDAO.getInstance().addUserCity(bjs);

		// 2.查询所有用户城市
//		 LogUtils.d(TAG, HandWeatherDAO.getInstance().getAllUserCities()
//		 .toString());

		// 3.通过城市名字删除用户城市
		// HandWeatherDAO.getInstance().deleteUserCity("武汉市");

		// 4.判断城市是不是存在
		// LogUtils.d(TAG,HandWeatherDAO.getInstance().isCityExists("武汉")+"");

		// 5.更新天气信息
//		UserCity bjs = new UserCity("北京市", "-4℃", "22℃", "阴天——", "1");
//		HandWeatherDAO.getInstance().updateWeatherInfo(bjs);

		//6.更新状态信息
//		HandWeatherDAO.getInstance().updateStatue("北京", "1");
		
		//7.用户表中记录的个数
		LogUtils.d(TAG, HandWeatherDAO.getInstance().getUserCitySize()+"长度");
		
	}

	/**
	 * 使用本类
	 * 
	 * @return
	 */
	public static MyApplaction getInstance() {
		return instance;
	}

}
