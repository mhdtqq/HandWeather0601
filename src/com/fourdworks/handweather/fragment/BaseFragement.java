package com.fourdworks.handweather.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 功能:本应用中所有的碎片都需要继承该碎片 作者:mike 时间：2015-10-26 上午10:24:06 修改:
 */
public abstract class BaseFragement extends Fragment {
	private int layoutId = -1;
	protected View mView;//本碎片的视图

	// 构造函数
	public BaseFragement(int layoutId) {
		super();
		this.layoutId = layoutId;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//初始化view
		if(layoutId!=-1){
			mView = inflater.inflate(layoutId, null);
		}
		
		initData();
		initView();
		bindView();
		
		return mView;
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

}
