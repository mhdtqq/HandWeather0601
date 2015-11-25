package com.fourdworks.handweather.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.fourdworks.handweather.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * ViewPager实现的轮播图广告自定义视图，如京东首页的广告轮播图效果； 既支持自动轮播页面也支持手势滑动切换页面
 * 
 */
@SuppressLint("HandlerLeak")
@SuppressWarnings("unused")
public class SlideShowView extends FrameLayout {
	public ImageLoader imageLoader;
	public DisplayImageOptions defLoadOption;

	private final static boolean isAutoPlay = true;
	private List<String> imageUris;
	private List<ImageView> imageViewsList;
	private List<ImageView> dotViewsList;
	private LinearLayout mLinearLayout;

	private ViewPager mViewPager;
	private int currentItem = 0;
	private ScheduledExecutorService scheduledExecutorService;
	private OnBranchClickListener myCallBack = null;

	// 首次切换延迟时间
	private int firstAutoPlayDelayTime = 2;
	// 两次切换之间的间隔时间
	private int autoPeriodTime = 5;

	public interface OnBranchClickListener {
		public void OnBranchClick(int position);
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			mViewPager.setCurrentItem(currentItem);
		}
	};

	public SlideShowView(Context context) {
		this(context, null);
	}

	public SlideShowView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlideShowView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub

		initUI(context);

		if (!(imageUris.size() <= 0)) {
			setImageUris(imageUris);
		}

		if (context instanceof OnBranchClickListener) {
			myCallBack = (OnBranchClickListener) context;
		}

		if (isAutoPlay) {
			startPlay();
		}
	}

	@SuppressWarnings("deprecation")
	private void initUI(Context context) {
		// if(isInEditMode()){
		// return;
		// }
		
		imageViewsList = new ArrayList<ImageView>();
		dotViewsList = new ArrayList<ImageView>();
		imageUris = new ArrayList<String>();

		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(context
				.getApplicationContext()));
		
		defLoadOption = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.img_loading_default_big)
				.showImageForEmptyUri(R.drawable.img_loading_empty_big)
				.showImageOnFail(R.drawable.img_loading_fail_big)
				.cacheInMemory(true).cacheOnDisc(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();

		LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this,
				true);
		
		mLinearLayout = (LinearLayout) findViewById(R.id.linearlayout);
		mViewPager = (ViewPager) findViewById(R.id.viewPager);
	}

	public void setImageUris(List<String> imageuris) {
		if (imageuris == null)
			return;
		
		imageViewsList.clear();
		dotViewsList.clear();
		mLinearLayout.removeAllViews();
		
		mViewPager.setAdapter(new MyPagerAdapter());
		
		imageUris.clear();
		imageUris.addAll(imageuris);
		// for (int i = 0; i < imageuris.size(); i++) {
		// imageUris.add(imageuris.get(i));
		// }
		//为点的imageView创建布局参数对象		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(5, 0, 0, 0);//间距
		
		//生成viewPager列表项图片视图集合和点的集合		
		for (int i = 0; i < imageUris.size(); i++) {
			try {
				//创建轮播视图图片集合				
				ImageView imageView = new ImageView(getContext());
				imageView.setTag(i);//为图片视图设置下标
				imageView.setScaleType(ScaleType.FIT_XY);// 充满屏幕
				imageLoader.displayImage(imageuris.get(i), imageView,
						defLoadOption);
				imageViewsList.add(imageView);
				//为轮播视图中的imageview添加单击监听器
				imageView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						int pos = (Integer) v.getTag();
						if (myCallBack != null) {
							myCallBack.OnBranchClick(pos);
						}
					}
				});
				//创建点的集合				
				ImageView viewDot = new ImageView(getContext());
				
				if (i == 0) {//默认显示第一个item
					viewDot.setBackgroundResource(R.drawable.dot_yes);
				} else {
					viewDot.setBackgroundResource(R.drawable.dot_no);
				}

				viewDot.setLayoutParams(lp);
				dotViewsList.add(viewDot);
				mLinearLayout.addView(viewDot);
			} catch (Exception e) {
			}
		}

		mViewPager.setFocusable(true);
		mViewPager.setAdapter(new MyPagerAdapter());
		mViewPager.setOnPageChangeListener(new MyPageChangeListener());
	}

	private void startPlay() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
							   //以固定的速率执行任务(任务对象Runnable,延迟时间,两次执行任务的时间间距，)						
		scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(),
				firstAutoPlayDelayTime, autoPeriodTime, TimeUnit.SECONDS);
	}

	private void stopPlay() {
		scheduledExecutorService.shutdown();
	}

	/**
	 * 设置选中的tip的背景
	 * 
	 * @param selectItems
	 */
	private void setImageBackground(int selectItems) {
		for (int i = 0; i < dotViewsList.size(); i++) {
			if (i == selectItems) {
				dotViewsList.get(i).setBackgroundResource(R.drawable.dot_yes);
			} else {
				dotViewsList.get(i).setBackgroundResource(R.drawable.dot_no);
			}
		}
	}

	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public void destroyItem(View container, int position, Object object) {
			// TODO Auto-generated method stub
			// ((ViewPag.er)container).removeView((View)object);
			try {
				((ViewPager) container)
						.removeView(imageViewsList.get(position));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		@Override
		public Object instantiateItem(View container, int position) {
			// TODO Auto-generated method stub
			((ViewPager) container).addView(imageViewsList.get(position));
			return imageViewsList.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageViewsList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
	}

	private class MyPageChangeListener implements OnPageChangeListener {

		boolean isAutoPlay = false;

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			switch (arg0) {
			case 1:
				isAutoPlay = false;
				break;
			case 2:
				isAutoPlay = true;
				break;
			case 0:
				if (mViewPager.getCurrentItem() == mViewPager.getAdapter()
						.getCount() - 1 && !isAutoPlay) {
					mViewPager.setCurrentItem(0);
				}

				else if (mViewPager.getCurrentItem() == 0 && !isAutoPlay) {
					mViewPager.setCurrentItem(mViewPager.getAdapter()
							.getCount() - 1);
				}
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int pos) {
			// TODO Auto-generated method stub
			setImageBackground(pos % imageUris.size());
		}
	}

	private class SlideShowTask implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized (mViewPager) {
				//计算下一个item
				currentItem = (currentItem + 1) % imageViewsList.size();
				handler.obtainMessage().sendToTarget();
			}
		}
	}

	private void destoryBitmaps() {
		for (int i = 0; i < imageViewsList.size(); i++) {
			ImageView imageView = imageViewsList.get(i);
			Drawable drawable = imageView.getDrawable();
			if (drawable != null) {

				drawable.setCallback(null);
			}
		}
	}
}
