package com.fourdworks.handweather.activity;

import com.fourdworks.handweather.R;
import com.fourdworks.handweather.beans.User;
import com.fourdworks.handweather.global.CostantValue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * 功能:仅供测试
 * 作者:mike
 * 时间：2015-10-22 上午11:41:14
 * 修改:
 */
public class TestActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View view = new View(this);
		view.setBackgroundColor(getResources().getColor(R.color.green));
		setContentView(view);
		
		//获取数据
		//1.获取意图
		Intent inIntent = getIntent();
		//2.intent--->data
		User dema = (User) inIntent.getSerializableExtra(CostantValue.KEY_INTENT);
		//3.显示		
		Toast.makeText(this, dema.toString(), 1000).show();
		
	}
}
