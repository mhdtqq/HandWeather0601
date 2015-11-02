package com.fourdworks.handweather.activity;

import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.fourdworks.handweather.R;
import com.fourdworks.handweather.global.CostantValue;

/**
 * 功能:忘记密码 作者:mike 时间：2015-10-27 上午10:43:45 修改:
 */
public class ForgetPwdActivity extends BaseActivity {
	// 倒计时效果
	// ------------------
	// 1.Thread+Handler
	// 2.Timer(计时器)+Handler
	// 3.Handler
	Button getAutocode;// 获取验证码
	int currentDJSTime;// 当前倒计时的时间
	// 倒计时handler
	Handler handler = new Handler() {
		
		public void handleMessage(Message msg) {
			// ---------------
			// 1.currentDJSTime< 1(计时结束)
			if (currentDJSTime < 1) {
				// ----
				// 1.将按钮恢复可点击状态
				getAutocode.setEnabled(true);
				// 2.按钮显示“获取验证码”
				getAutocode.setText("获取验证码");
			} else {
				// 2.currentDJSTime>0(计时进行中)
				// 1.currentDJSTime减1
				// currentDJSTime--;
				// 2.按钮显示时间
				String htmlText = "<font color=#ff0000>" + (--currentDJSTime)
						+ "S</font>后重新获取";
				getAutocode.setText(Html.fromHtml(htmlText));
				// 3.延时1S（Handler）
				handler.sendEmptyMessageDelayed(0, 1000);
			}
		};
	};

	public ForgetPwdActivity() {
		super(R.layout.activity_forgetpwd);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		// 初始化
		getAutocode = (Button) findViewById(R.id.get_autocode);

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void bindView() {
		// TODO Auto-generated method stub
		// 获取验证码/注册单击监听器
		getAutocode.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 1.禁用此按钮
				getAutocode.setEnabled(false);
				// 2.currentDJSTime= GET_AUTOCODE_EFFECT_TIME
				currentDJSTime = CostantValue.GET_AUTOCODE_EFFECT_TIME;

				// 3.按钮显示时间
				String htmlText = "<font color=#ff0000>" + currentDJSTime
						+ "S</font>后重新获取";
				getAutocode.setText(Html.fromHtml(htmlText));

				// 4.延时1S（Handler）
				handler.sendEmptyMessageDelayed(0, 1000);
			}
		});
	}

}
