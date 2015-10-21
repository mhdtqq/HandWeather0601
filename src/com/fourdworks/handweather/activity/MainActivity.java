package com.fourdworks.handweather.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;

import com.fourdworks.handweather.R;
import com.fourdworks.handweather.fragment.CenterFragement;
import com.fourdworks.handweather.fragment.LeftFragement;
import com.fourdworks.handweather.fragment.RightFragement;
import com.fourdworks.handweather.view.SlidingMenu;

/**
 * 功能: 作者:mike 时间：2015-10-21 上午9:28:26 修改:
 */
public class MainActivity extends FragmentActivity {
	// 声明控件
	public SlidingMenu mSlidingMenu;// 侧滑菜单	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 一、初始化控件
		mSlidingMenu = (SlidingMenu) findViewById(R.id.mSlidingMenu);

		// 二、为侧滑侧单添加左、中、右三个页面
		// 2.1创建三个视图
		// 创建一个布局转换器
		LayoutInflater inflater = LayoutInflater.from(this);
		// 将布局转换成视图
		View leftView = inflater.inflate(R.layout.left, null);
		View centerView = inflater.inflate(R.layout.center, null);
		View rightView = inflater.inflate(R.layout.right, null);

		// 2.2将三个视图设置到侧滑菜单[中间页面必须放在最后设置]
		mSlidingMenu.setLeftView(leftView);
		mSlidingMenu.setRightView(rightView);
		mSlidingMenu.setCenterView(centerView);
		
		// 三.将左、中、右三个页面替换成碎片
		// 3.1获取碎片管理器
		FragmentManager fm = getSupportFragmentManager();
		// 3.2开始碎片事务
		FragmentTransaction ft = fm.beginTransaction();
		
		// ---将布局替换成碎片----
		ft.replace(R.id.left_Frame, new LeftFragement());
		ft.replace(R.id.right_Frame, new RightFragement());
		ft.replace(R.id.center_Frame, new CenterFragement());
		
		// ------------------
		// 3.2提交碎片事务
		ft.commit();
	}

	/**
	 * 向左滑动
	 * 
	 * @param v
	 */
	public void toLeft(View v) {
		// TODO Auto-generated method stub
		// 左侧面对象.显示左侧面
		mSlidingMenu.showLeftView();
	}

	/**
	 * 向右滑动
	 * 
	 * @param v
	 */
	public void toRight(View v) {
		// TODO Auto-generated method stub
		// //左侧面对象.显示右侧面
		mSlidingMenu.showRightView();
	}

}
