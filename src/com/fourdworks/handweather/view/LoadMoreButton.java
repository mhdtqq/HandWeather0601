package com.fourdworks.handweather.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.fourdworks.handweather.R;

//查看更多自定义组件的设计
//-------------------------
//1.界面
//2.方法
//------------------
//  隐藏查看更多
//  显示查看更多
//  显示加载更多动画       
//  显示加载更多 
//  查看更多按钮注册单击监听器        

/**
 * 功能:查看更多按钮 作者:mike 时间：2015-11-24 上午9:31:07 修改:
 */
public class LoadMoreButton extends FrameLayout {
	private Context c;

	// 控件声明
	private Button loadMoreBtn;// 加载更多按钮
	private ProgressBar loadMoreAnim;// 加载更多动画

	public LoadMoreButton(Context context) {
		super(context);
		init();

		// 查看更多按钮注册单击监听器
		// 1.显示加载更多动画
		// 2.做一个加载更多按钮的接口：具体的行为有使用者来设定
		loadMoreBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 1.显示加载更多动画
				startLoading();
				// 2.做一个加载更多按钮的接口：具体的行为有使用者来设定
				if (onLoadMoreClickListener != null) {
					// 调用接口的单击事件
					onLoadMoreClickListener.onLoadMoreClick(loadMoreBtn);
				}

			}
		});

		startLoading();// 启动加载动画
	}

	/**
	 * 初始化
	 */
	private void init() {
		c = getContext();
		// 布局文件嵌入到当前视图中
		LayoutInflater.from(c).inflate(R.layout.item_loadmore, this);
		// 初始化控件
		loadMoreBtn = (Button) findViewById(R.id.load_more_btn);// 加载更多按钮
		loadMoreAnim = (ProgressBar) findViewById(R.id.load_more_anim);// 加载更多动画
	}

	// 隐藏查看更多
	public void hideSelf(ListView listView) {
		setVisibility(View.GONE);
		
		// 移除自己
		listView.removeFooterView(this);
	}

	// 显示查看更多
	public void showSelf() {
		setVisibility(View.VISIBLE);
	}

	// 显示加载更多动画
	public void startLoading() {
		// 先隐藏查看更多
		loadMoreBtn.setVisibility(View.GONE);
		// 显示加载动画
		loadMoreAnim.setVisibility(View.VISIBLE);
	}

	// 显示加载更多
	public void finishLoading() {
		// 先隐藏加载动画
		loadMoreAnim.setVisibility(View.GONE);
		// 显示查看更多
		loadMoreBtn.setVisibility(View.VISIBLE);

	}

	// 加载更多单击监听器的引用
	private OnLoadMoreClickListener onLoadMoreClickListener;

	/**
	 * 设置单击加载更多监听器
	 * 
	 * @param onLoadMoreClickListener
	 */
	public void setOnLoadMoreClickListener(
			OnLoadMoreClickListener onLoadMoreClickListener) {
		this.onLoadMoreClickListener = onLoadMoreClickListener;
	}

	// 加载更多的接口
	public interface OnLoadMoreClickListener {
		public void onLoadMoreClick(Button btn);
	}
}
