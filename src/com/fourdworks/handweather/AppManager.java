package com.fourdworks.handweather;

import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;

/**
 * 功能:程序管理器（负责程序的退出） 
 * 作者:mike 
 * 时间：2015-10-23 
 * 上午9:25:59 
 * 修改:
 */
public class AppManager {

	// 定义自己
	private static AppManager instance;
	
	// 1.堆栈：用于保存所有的Activity
	private Stack<Activity> allActivities;
	
	//锁
	private static Integer loc = 1;
	
	// 单例的使用
	
	/**
	 * 私有化本类
	 */
	private AppManager() {
		allActivities = new Stack<Activity>();
	}

	/**
	 * 使用本类的唯一入口
	 * 
	 * @return
	 */
	public static AppManager getInstance() {
		// 使用互斥锁的缺点：效率低
		// 本方法只有在第一次执行的时候， instance == null,以后每一次执行都不为null
		
		if (instance == null)
			synchronized (loc) {
				return instance != null ? instance
						: (instance = new AppManager());
			}

		return instance;
	}

	/**
	 * 将Acitivity保存到堆栈中
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		// 将Activity的对象保存到堆栈中
		allActivities.add(activity);
	}

	/**
	 * 干掉所有的Activity
	 */
	public void finishAllActivity() {
		// 遍历堆栈
		for (Activity activity : allActivities) {
			if (activity != null && !activity.isFinishing())
				activity.finish();
		}
	}
	
	/**
	 * 程序退出
	 */
	public void appExit() {
		//1.杀掉所有的Activity
		finishAllActivity();
		//2.重启包
		ActivityManager am = (ActivityManager) MyApplaction.getInstance().getSystemService(MyApplaction.ACTIVITY_SERVICE);
		am.restartPackage(MyApplaction.getInstance().getPackageName());
		
		//3.System.exit()
		System.exit(0);
	}

}
