package com.fourdworks.handweather.adapter;

import java.util.List;

import com.fourdworks.handweather.R;
import com.fourdworks.handweather.activity.MainActivity;
import com.fourdworks.handweather.beans.UserCity;
import com.fourdworks.handweather.db.HandWeatherDAO;
import com.fourdworks.handweather.utils.MyTextUtils;
import com.fourdworks.handweather.utils.TweensUtils;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 功能:左侧页面的用户城市适配器 作者:mike 时间：2015-11-6 上午11:06:55 修改:
 */
public class UserCityAdapter extends BaseAdapter {
	private Context c;
	private List<UserCity> listUserCities;

	private boolean isInEditState;// 是否在编辑状态

	/**
	 * 判断当前城市是否在编辑状态
	 * 
	 * @return
	 */
	public boolean isInEditState() {
		return isInEditState;
	}

	/**
	 * 设置用户城市是否是编辑状态
	 * 
	 * @param isInEditState
	 */
	public void setInEditState(boolean isInEditState) {
		this.isInEditState = isInEditState;
	}

	public UserCityAdapter(Context c, List<UserCity> listUserCities) {
		super();
		this.c = c;
		this.listUserCities = listUserCities;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listUserCities.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listUserCities.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		final ViewHolder holder;

		if (v == null) {// 当前列表项视图为空
			// 初始化当前列表项视图
			v = LayoutInflater.from(c).inflate(R.layout.item_usercity, null);
			// 初始化当前列表项的控件保存到holder中
			holder = new ViewHolder();
			holder.isSureDelete = (ImageButton) v
					.findViewById(R.id.is_sure_deletecity);
			holder.cityName = (TextView) v.findViewById(R.id.city_name);
			holder.tempratureRange = (TextView) v
					.findViewById(R.id.temprature_range);
			holder.weatherIcon = (ImageView) v.findViewById(R.id.weather_icon);
			holder.loadWeather = (ProgressBar) v
					.findViewById(R.id.load_weather);
			holder.sureDelete = (ImageButton) v
					.findViewById(R.id.sure_deletecity);

			// 装包
			v.setTag(holder);

		} else {
			// 卸包
			holder = (ViewHolder) v.getTag();
		}

		// 获取当前列表项的数据模型
		UserCity data = (UserCity) getItem(position);

		// 是否在编辑状态
		if (isInEditState) {// 在编辑状态
			// 是否删除图标可见
			holder.isSureDelete.setVisibility(View.VISIBLE);
		} else {// 没有在编辑状态
				// 清除动画
			holder.isSureDelete.clearAnimation();

			holder.isSureDelete.setVisibility(View.GONE);

			// 如果删除图标也显示的话，就隐藏删除图标
			holder.sureDelete.setVisibility(View.GONE);
		}

		// 为是否删除按钮注册单击监听器
		holder.isSureDelete.setTag(holder.sureDelete);

		holder.isSureDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 取出删除图标
				ImageButton sureDelete = (ImageButton) v.getTag();

				if (sureDelete.getVisibility() == View.VISIBLE) {
					// 是否删除图标从90~180旋转
					TweensUtils.startRotateAnim(v, 90, 180, 200);
					// 隐藏删除图标
					sureDelete.setVisibility(View.GONE);
					// 隐藏加载动画
					holder.loadWeather.setVisibility(View.VISIBLE);
				} else {
					// 是否删除图标从0~90旋转
					TweensUtils.startRotateAnim(v, 0, 90, 200);
					// 显示删除图标
					sureDelete.setVisibility(View.VISIBLE);
					// 隐藏加载动画
					holder.loadWeather.setVisibility(View.GONE);
				}

			}
		});

		// 为删除按钮注册单击监听器
		// 将id打包到删除图标中
		holder.sureDelete.setTag(position);

		holder.sureDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 取出删除按钮对应的id
				int pos = (Integer) v.getTag();

				// 1.判断当前城市是否是选中城市（获取状态）
				boolean isCurrentCity = listUserCities.get(pos).getStatue()
						.equals("1") ? true : false;

				// 2.将该城市从用户表中删除
				HandWeatherDAO.getInstance().deleteUserCity(
						listUserCities.get(pos).getCity());

				// 3.如果当前城市是选中城市，就将用户城市表中的第一个城市设置为当前选中城市
				if (isCurrentCity) {
					HandWeatherDAO.getInstance().setCurrentCityFirst();
				}

				// 4.将适配器改为未编辑状态，改变编辑图标
				((MainActivity) c).leftFragement.setOutSideEdit();

				// 5.从集合中查询出所有用户城市集合，有就更新适配器，没有就跳转到添加城市页面
				// 可以调用refreshUserCityList
				((MainActivity) c).leftFragement.refreshUserCityList();

				// 6.如果当前需要删除的城市时当前选中城市，改变中间页面的标题，以及天气信息
				if (isCurrentCity) {
					((MainActivity) c).centerFragement.refreshCityInfo();
				}
				
				// //7.显示中间页面
				if (HandWeatherDAO.getInstance().getUserCitySize() > 0) {
					((MainActivity) c).mSlidingMenu.showLeftView();
				}
			}
		});

		// 城市名字
		holder.cityName.setText(MyTextUtils.getCity(data.getCity()));

		// 是否有天气信息
		if (TextUtils.isEmpty(data.getLowtemp())
				&& TextUtils.isEmpty(data.getHightemp())
				&& TextUtils.isEmpty(data.getWeatherinfo())) {// 否
			// 显示加载中
			holder.tempratureRange.setText("加载中");
			// 显示加载动画
			holder.loadWeather.setVisibility(View.VISIBLE);
			// 隐藏天气图标
			holder.weatherIcon.setVisibility(View.INVISIBLE);
		} else {// 是
				// 显示温度范围
			holder.tempratureRange.setText(data.getLowtemp() + "~"
					+ data.getHightemp());
			// 隐藏加载动画
			holder.loadWeather.setVisibility(View.GONE);
			// 显示天气图标
			holder.weatherIcon.setVisibility(View.VISIBLE);

			// 显示什么样的天气图标
			// ??????????????????????????????
		}
		
		//标记当前选中城市
		if(data.getStatue().equals("1")){
			v.setBackgroundColor(0xff5D5C5C);
		}else{
			v.setBackgroundColor(0x00000000);
		}

		
		
		
		//为当前列表项注册单击事件
		v.findViewById(R.id.tag).setTag(position);
		v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//获取当前列表项的下标
				int pos = (Integer) v.findViewById(R.id.tag).getTag();
				
				//1.将该城市设置为当前选中城市
				HandWeatherDAO.getInstance().setCurrentCity(listUserCities.get(pos).getCity());
				
				//2.调用中间页面的相关方法，去更新标题和天气信息
				((MainActivity)c).centerFragement.refreshCityInfo();
				
				//3.显示中间页面
				((MainActivity)c).mSlidingMenu.showLeftView();
				
				//4.更新左页面的城市集合
				((MainActivity)c).leftFragement.refreshUserCityList();
			}
		});
		return v;
	}
	
	class ViewHolder {
		ImageButton isSureDelete;// 是否确定删除
		TextView cityName;// 城市名字
		ImageView weatherIcon;// 天气图标
		TextView tempratureRange;// 温度范围
		ProgressBar loadWeather;// 加载天气的动画
		ImageButton sureDelete;// 确认删除
	}

}
