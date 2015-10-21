package com.fourdworks.handweather.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fourdworks.handweather.R;

/**
 * 功能:右侧面碎片（设置）
 * 作者:mike
 * 时间：2015-10-21 上午10:40:14
 * 修改:
 */
public class RightFragement extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//初始化本碎片的视图
		View view = inflater.inflate(R.layout.frag_right, null);
		
		return view;
	}
}
