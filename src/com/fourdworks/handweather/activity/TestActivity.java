package com.fourdworks.handweather.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.fourdworks.handweather.R;
import com.fourdworks.handweather.db.HandWeatherDAO;
import com.fourdworks.handweather.global.CostantValue;
import com.fourdworks.handweather.utils.LogUtils;

//错误
//---------------------
//语法错误
//逻辑错误

//逻辑错误
//---------------------
//1.直接造成程序奔溃(logcat---->error---->caused by----->下一行日志)
//2.不奔溃，但是结果错了(Log日志，断点调试(debug))



//Log日志
//过滤器：
//等级：error>warn>info>debug>verbose


//断点调试(debug)：跟踪代码的执行过程
//F5：进入方法
//F6:单步执行
//F7:返回方法(跳出方法)
//F8:结束代码执行(从一个断点调到另一个断点)



//LogUtils工具的设计





//
/**
 * 功能:仅供测试 作者:mike 时间：2015-10-22 上午11:41:14 修改:
 */
public class TestActivity extends Activity {
	private String TAG = "TestActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);

		// //获取数据
		// //1.获取意图
		// Intent inIntent = getIntent();
		// //2.intent--->data
		// User dema = (User)
		// inIntent.getSerializableExtra(CostantValue.KEY_INTENT);
		// //3.显示
		// Toast.makeText(this, dema.toString(), 1000).show();
		//
		// int a = 10;
		// int b = 11;
		// int c= 16;
		// int abSum = a+c;
		// int div = 45/0;
		
//		//数据库
//		SQLiteDatabase db = this.openOrCreateDatabase("test", MODE_PRIVATE, null);
//		//创建表
//		db.execSQL("CREATE TABLE user (name varchar(10),sex varchar(2),loves varchar(50))");
	HandWeatherDAO handWeatherDAO = HandWeatherDAO.getInstance();
	
	
	}

	/**
	 * @param v
	 */
	public void getResult(View v) {
		EditText first = (EditText) findViewById(R.id.firstData);
		EditText second = (EditText) findViewById(R.id.secondData);
		EditText result = (EditText) findViewById(R.id.result);
		
		int firstData = Integer.parseInt(first.getText().toString());
		int secondData = Integer.parseInt(second.getText().toString());
		
//		Log.d(TAG, "第一个数："+firstData);
//		Log.d(TAG, "第而二个数："+secondData);
//		if(CostantValue.IS_ONLINE){
//			Log.d(TAG, "第而二个数："+secondData);
//		}
		
		LogUtils.d(TAG, "第而二个数："+secondData);
		
		String resultStr = getSum(firstData, secondData);
		
//		Log.d(TAG, "日志等级debug");
//		Log.e(TAG, "日志等级error");
//		Log.i(TAG, "日志等级info");
//		Log.v(TAG, "日志等级verbose");
//		Log.w(TAG, "日志等级warn");
		
		result.setText(resultStr);
	}
	
	/**
	 * 求和
	 * @param a
	 * @param b
	 * @return
	 */
	private String getSum(int a,int b){
		String resultData = null;
		if (b == 0) {
			resultData = "error";
		} else {
			resultData = a / 12 + "";
		}
		
		return resultData;
	}
	
}
