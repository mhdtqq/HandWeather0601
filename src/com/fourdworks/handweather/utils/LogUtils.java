package com.fourdworks.handweather.utils;

import android.util.Log;

import com.fourdworks.handweather.global.CostantValue;

/**
 * 功能:Log打印工具：所有的Log输出，都需要通过本类
 * 作者:mike
 * 时间：2015-10-29 上午11:34:59
 * 修改:
 */
public class LogUtils {
	/**
	 * Error
	 * @param TAG
	 * @param message
	 */
	public static void e(String TAG,String message){
		if(!CostantValue.IS_ONLINE){
			Log.e(TAG, message);
		}
	}
	
	/**
	 * Info
	 * @param TAG
	 * @param message
	 */
	public static void i(String TAG,String message){
		if(!CostantValue.IS_ONLINE){
			Log.i(TAG, message);
		}
	}
	
	/**
	 * Warn
	 * @param TAG
	 * @param message
	 */
	public static void w(String TAG,String message){
		if(!CostantValue.IS_ONLINE){
			Log.w(TAG, message);
		}
	}
	
	/**
	 * Debug
	 * @param TAG
	 * @param message
	 */
	public static void d(String TAG,String message){
		if(!CostantValue.IS_ONLINE){
			Log.d(TAG, message);
		}
	}
		
	/**
	 * Warn
	 * @param TAG
	 * @param message
	 */
	public static void v(String TAG,String message){
		if(!CostantValue.IS_ONLINE){
			Log.v(TAG, message);
		}
	}
}
