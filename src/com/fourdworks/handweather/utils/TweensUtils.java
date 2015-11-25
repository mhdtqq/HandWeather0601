package com.fourdworks.handweather.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

/**
 * 功能:补件动画的工具类
 * 作者:mike
 * 时间：2015-11-18 上午11:33:39
 * 修改:
 */
public class TweensUtils {
	
	/**
	 * 启动旋转动画
	 * @param fromDegree
	 * 		 旋转的起始角度	
	 * @param endDegree
	 * 		旋转的终止角度	
	 */
	public static void startRotateAnim(View v,int fromDegree,int endDegree,int duration){ 
		//1.创建旋转动画的对象                                                                                               
		RotateAnimation animation = new RotateAnimation(fromDegree, endDegree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		
		//2.配置旋转动画的参数
		animation.setDuration(200);
		animation.setFillAfter(true);//动画停止在动画的终止状态
		//3.启动动画		
		v.startAnimation(animation);
	}
}
