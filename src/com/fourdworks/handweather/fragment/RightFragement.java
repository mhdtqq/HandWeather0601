package com.fourdworks.handweather.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.fourdworks.handweather.R;
import com.fourdworks.handweather.activity.BaseActivity;
import com.fourdworks.handweather.activity.LoginActivity;

/**
 * 功能:右侧面碎片（设置） 作者:mike 时间：2015-10-21 上午10:40:14 修改:
 */
public class RightFragement extends BaseFragement {
	// 声明控件
	GridView toolBas;
	SimpleAdapter toolBasAdapter;//工具栏的适配器
	int[] titles;//工具栏标题数组
	
	public RightFragement() {
		super(R.layout.frag_right);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		// 初始化控件
		toolBas = (GridView) mView.findViewById(R.id.toolbars);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

		// 快速工具栏适配器
		List<Map<String, Object>> toolBasData = new ArrayList<Map<String, Object>>();

		// 消息中心
		// 图标数组
		int[] icons = { R.drawable.message_icon, R.drawable.rank_icon,
				R.drawable.collect_iocn, R.drawable.vip_icon,
				R.drawable.playhis_icon };
		// 标题数组
		titles = new int[]{ R.string.toolbas_message, R.string.toolbas_rank,
				R.string.toolbas_collection, R.string.toolbas_playhis,
				R.string.toolbas_vip };

		// 初始化快速工具栏的集合
		for (int i = 0; i < icons.length; i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("icon", icons[i]);
			item.put("title", getResources().getString(titles[i]));

			// 加入集合
			toolBasData.add(item);
		}

		toolBasAdapter = new SimpleAdapter(this.getActivity(),
				toolBasData, R.layout.item_bars,
				new String[] { "icon", "title" }, new int[] {
						R.id.toolbas_icon, R.id.toolbas_title });

	}

	@Override
	protected void bindView() {
		// TODO Auto-generated method stub
		// 设置适配器
		toolBas.setAdapter(toolBasAdapter);
		
		// 为工具列表注册条目监听器
		toolBas.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				((BaseActivity) getActivity()).showMessage(getResources()
						.getString(titles[position]));
			}
		});
	}
	


}
