package com.fourdworks.handweather.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.fourdworks.handweather.AppManager;
import com.fourdworks.handweather.R;
import com.fourdworks.handweather.fragment.CenterFragement;
import com.fourdworks.handweather.fragment.LeftFragement;
import com.fourdworks.handweather.fragment.RightFragement;
import com.fourdworks.handweather.global.CostantValue;
import com.fourdworks.handweather.global.VariableValue;
import com.fourdworks.handweather.view.SlidingMenu;

/**
 * 功能: 作者:mike 时间：2015-10-21 上午9:28:26 修改:
 */
public class MainActivity extends BaseActivity {

	// 声明控件
	public SlidingMenu mSlidingMenu;// 侧滑菜单

	// 通过构造函数将布局id传到BaseActivity里面去
	public MainActivity() {
		super(R.layout.activity_main);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
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
	}

	@Override
	protected void bindView() {
		// TODO Auto-generated method stub
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

	/**
	 * 测试方法
	 * 
	 * @param v
	 */
	public void test(View v) {
		// 测试跳转
		// jumpToActivity(TestActivity.class);
		// jumpToActivity(TestActivity.class, new User("德玛", 28, "钻草丛！"));
		this.finish();
	}
	
	/**
	 * 捕获手机的返回事件：实现双击退出
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		// 判断按键是不是返回按键的键值
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 判断本次点击距离上一次单击是不是小于2S(123545121212-lastTime<appExitTime)
			if (System.currentTimeMillis() - VariableValue.lastTime <= CostantValue.APPEXITTIME) {
				// true:退出
				AppManager.getInstance().appExit();
			} else {
				// false:lastTime = 123545121212
				VariableValue.lastTime = System.currentTimeMillis();
				// 提示
				showMessage("再按一次，退出应用程序！");
			}
		}
			
		return true;
	}
	
	/**
	 * 跳到登录页面
	 * @param view
	 */
	public void goLogin(View view){
		jumpToActivity(LoginActivity.class);
	}
}
