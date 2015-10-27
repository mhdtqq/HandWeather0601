package com.fourdworks.handweather.activity;

import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.fourdworks.handweather.R;

/**
 * 功能:注册页面 作者:mike 时间：2015-10-27 上午9:05:46 修改:
 */
public class RegisterActivity extends BaseActivity {
	// 声明控件
	RadioGroup registerWayPanel;
	View mobilePanel, emailePanel;// 手机号、邮箱面板

	public RegisterActivity() {
		// TODO Auto-generated constructor stub
		super(R.layout.activity_register);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		registerWayPanel = (RadioGroup) findViewById(R.id.register_way_panel);
		mobilePanel = findViewById(R.id.mobile_register_panel);
		emailePanel = findViewById(R.id.email_register_panel);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void bindView() {
		// TODO Auto-generated method stub
		// 为单选按钮组注册选中变化监听器
		registerWayPanel
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						if (checkedId == R.id.mobile_register) {// 手机号注册
							mobilePanel.setVisibility(View.VISIBLE);// 显示手机号面板
							emailePanel.setVisibility(View.GONE);// 隐藏邮箱面板

						} else {// 邮箱注册
							mobilePanel.setVisibility(View.GONE);// 显示手机号面板
							emailePanel.setVisibility(View.VISIBLE);// 隐藏邮箱面板
						}

					}
				});
	}

	/**
	 * 返回上一页面
	 * 
	 * @param v
	 */
	public void back(View v) {
		finish();
	}

}
