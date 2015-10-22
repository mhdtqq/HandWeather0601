package com.fourdworks.handweather.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fourdworks.handweather.R;


/**
 * 功能:主页面----新闻碎片
 * 作者:mike
 * 时间：2015-10-22 上午8:53:06
 * 修改:
 */
public class NewsFragement extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//初始化本碎片的视图
		View view = inflater.inflate(R.layout.frag_news, null);
		
		return view;
	}
}
