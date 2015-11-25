package com.fourdworks.handweather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 功能: android中scrollview与listview共存 
 * 作者:mike 
 * 时间：2015-11-23 上午11:07:51 
 * 修改:
 */
public class MyListView extends ListView {

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyListView(Context context) {
		super(context);
	}

	/**
	 * 重新测量当前视图的高度
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
