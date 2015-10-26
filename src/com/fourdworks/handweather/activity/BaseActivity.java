package com.fourdworks.handweather.activity;

import java.io.Serializable;

import com.fourdworks.handweather.AppManager;
import com.fourdworks.handweather.global.CostantValue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.widget.Toast;

//基类中执行共同代码，而子类实现各自不同的代码

/**
 * 功能:Activity的基类，本app的每一个Activity都需要继承本Activity进行页面开发 作者:mike 时间：2015-10-22
 * 上午10:50:32 修改:
 */
public abstract class BaseActivity extends FragmentActivity {
	int layoutId = -1;// 布局id

	public BaseActivity(int layoutId) {
		// TODO Auto-generated constructor stub
		this.layoutId = layoutId;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//将本Activity保存到堆栈中
		AppManager.getInstance().addActivity(this);
		// 设置界面
		if (layoutId != -1) {
			setContentView(layoutId);
		}

		// 初始化数据
		initData();

		// 初始化界面
		initView();

		// 数据绑定视图
		bindView();
	}

	/**
	 * 定义初始化界面的抽象方法
	 */
	protected abstract void initView();

	/**
	 * 定义初始化数据的抽象方法
	 */
	protected abstract void initData();

	/**
	 * 定义数据和控件发生关系的抽象方法
	 */
	protected abstract void bindView();

	// ###################################################

	// 1.直接从当前Activity跳到其它Activity

	public void jumpToActivity(Class<?> cls) {
		jumpToActivity(cls, null);
	}
	
	// 2.直接从当前Activity跳到其它Activity,传递数据
	public void jumpToActivity(Class<?> cls, Serializable data) {
		// 创建intent
		Intent intent = new Intent(this, cls);
		// 设置数据
		if (data != null)
			intent.putExtra(CostantValue.KEY_INTENT, data);
		// 跳转
		startActivity(intent);
	}
	
	// 3.从当前Activity跳到其它Activity,并且需要回调
	public void jumpToActivityForResult(Class<?> cls,
			int requestCode) {
		jumpToActivityForResult(cls, null, requestCode);
	}
	
	// 4.从当前Activity跳到其它Activity,传递数据，并且需要回调
	public void jumpToActivityForResult(Class<?> cls,
			Serializable data, int requestCode) {
		// 创建意图
		Intent intent = new Intent(this, cls);

		// 传递数据
		if (data != null)
			intent.putExtra(CostantValue.KEY_INTENT, data);
		// 以回调的方式跳转
		startActivityForResult(intent, requestCode);
	}
	
	
	/**
	 * 使用Toast提示
	 * @param msg
	 */
	public void showMessage(String msg){
		//创建Toast对象
		Toast toast = Toast.makeText(this, msg, 1000);
//		//设置Toast相对于屏幕的位置
//		                //(对齐方式，X方向上的偏移量，Y方向上的偏移量)
//		toast.setGravity(Gravity.CENTER, 0, 0);
		
		//显示
		toast.show();
		
	}
	
}
