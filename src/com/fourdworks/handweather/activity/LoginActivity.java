package com.fourdworks.handweather.activity;

import android.view.View;

import com.fourdworks.handweather.R;

/**
 * 功能:登录页面 作者:mike 时间：2015-10-26 上午10:48:51 修改:
 */
public class LoginActivity extends BaseActivity {

	public LoginActivity() {
		super(R.layout.activity_login);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void bindView() {
		// TODO Auto-generated method stub

	}

	/**
	 * 返回上一页面
	 * 
	 * @param v
	 */
	public void back(View v) {
		finish();
	}
	
	/**
	 * 去忘记密码页面
	 */
	public void getForgetPwd(View v){
		jumpToActivity(ForgetPwdActivity.class);
	}
	
	/**
	 * 去注册页面
	 */
	public void toRegister(View v){
		jumpToActivity(RegisterActivity.class);
	}
}
