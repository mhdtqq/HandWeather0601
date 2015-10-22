package com.fourdworks.handweather.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fourdworks.handweather.R;
import com.fourdworks.handweather.activity.MainActivity;

//ViewPager与侧滑菜单发生了冲突
//--------------------------------
//1.当滑动到娱乐页面时，无法滑动到侧滑菜单右侧页面，因为侧滑菜单默认不支持向右滑动
//2.当滑动到娱乐页面时，无法滑动到新闻页面，因为ViewPager与其外侧的侧滑菜单发生了冲突，默认侧滑菜单可以滑动
//解决方法
//--------------------------------
//1.在天气页面，打开侧滑菜单的向左滑动，关闭向右滑动
//2.在新闻页面，关闭侧滑菜单的向左滑动，关闭向右滑动
//3.在娱乐页面，关闭侧滑菜单的向左滑动，打开向右滑动

/**
 * 功能:中间页面碎片（天气） 作者:mike 时间：2015-10-21 上午10:40:14 修改:
 */
public class CenterFragement extends Fragment {
	// 控件初始化
	ViewPager myViewPager;
	List<Fragment> homeListFragments;
	List<ImageView> listTitleCursors;//标题对应游标集合
	List<RelativeLayout> listTitles;//标题集合
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// 初始化本碎片的视图
		View view = inflater.inflate(R.layout.frag_center, null);

		// 初始化控件
		myViewPager = (ViewPager) view.findViewById(R.id.myViewPager);
		//初始化标题游标集合
		listTitleCursors = new ArrayList<ImageView>();
		listTitleCursors.add((ImageView) view.findViewById(R.id.title_cursor_1));
		listTitleCursors.add((ImageView) view.findViewById(R.id.title_cursor_2));
		listTitleCursors.add((ImageView) view.findViewById(R.id.title_cursor_3));
		
		
		//初始化标题集合
		listTitles = new ArrayList<RelativeLayout>();
		listTitles.add((RelativeLayout) view.findViewById(R.id.title_1));
		listTitles.add((RelativeLayout) view.findViewById(R.id.title_2));
		listTitles.add((RelativeLayout) view.findViewById(R.id.title_3));
		
		
		// 初始化碎片集合(天气，新闻，娱乐)
		homeListFragments = new ArrayList<Fragment>();
		homeListFragments.add(new WeatherFragement());
		homeListFragments.add(new NewsFragement());
		homeListFragments.add(new HappyFragement());
		
		// 将适配器设置到ViewPager
		myViewPager.setAdapter(new MyViewPagerAdp(getFragmentManager()));

		// 为myViewPager设置滑动监听器
		myViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			/*
			 * 页面被选中回调该方法
			 */
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				// 解决方法
				// --------------------------------
				// 1.在天气页面，打开侧滑菜单的向左滑动，关闭向右滑动
				// 2.在新闻页面，关闭侧滑菜单的向左滑动，关闭向右滑动
				// 3.在娱乐页面，关闭侧滑菜单的向左滑动，打开向右滑动
				switch (position) {
				case 0://在天气页面
					((MainActivity)getActivity()).mSlidingMenu.setCanSliding(true, false);
					break;
				case 1://在新闻页面
					((MainActivity)getActivity()).mSlidingMenu.setCanSliding(false, false);
					break;
				case 2://在娱乐页面
					((MainActivity)getActivity()).mSlidingMenu.setCanSliding(false, true);
					break;
				}
				
				
				//显示当前标题的游标
				showCurrenTitlteCursor(position);
			}

			/*
			 * 页面滚动过程中会回调该方法
			 */
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// TODO Auto-generated method stub
				//Log.d("Test", positionOffset+"~~~~~~~~~~"+positionOffsetPixels);
				
			}

			/*
			 * 当页面状态改变会回调该方法
			 */
			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
//				Log.d("Test", 
//						"状态"+state);
			}
		});
		
		
		
		//为标题注册单击事件
		for(int i = 0;i<listTitles.size();i++){
			//获取当前标题的空间对象
			RelativeLayout title = listTitles.get(i);
			
			//将下标设置到标题控件中
			title.setTag(i);
			
			//为标题注册单击事件
			title.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//取出当前标题对应的下表
					int postion = (Integer) v.getTag();
					
					
					// TODO Auto-generated method stub
					//1.显示对应游标(因为在设置页面变化时，对应的页面变化监听器会执行)
					//showCurrenTitlteCursor(postion);
					
					//2.显示对应的页面
					myViewPager.setCurrentItem(postion, true);
				}
			});
		}

		return view;
	}

	// myViewPager的碎片适配器----->三个碎片---->集合
	class MyViewPagerAdp extends FragmentPagerAdapter {

		public MyViewPagerAdp(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		/*
		 * 获取当前ViewPager显示的item
		 */
		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			return homeListFragments.get(position);
		}

		/*
		 * 获取当前item的总条目数
		 */
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return homeListFragments.size();
		}
	}
	
	/**
	 * 显示当前页面的标题的下标
	 * @param position
	 *        当前选中页面的下标 
	 */
	private void showCurrenTitlteCursor(int position) {
		// TODO Auto-generated method stub
		for(int i = 0;i<listTitleCursors.size();i++){
			//获取当期标题的控件对象
			ImageView titleCursor = listTitleCursors.get(i);
			if(i==position){//当前标题是选中标题
				titleCursor.setVisibility(View.VISIBLE);
			}else{
				titleCursor.setVisibility(View.INVISIBLE);
			}
		}
		
	}

}
