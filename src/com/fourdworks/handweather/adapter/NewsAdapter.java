package com.fourdworks.handweather.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fourdworks.handweather.R;
import com.fourdworks.handweather.beans.New;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * 功能:新闻列表的适配器 作者:mike 时间：2015-11-23 上午9:50:22 修改:
 */
public class NewsAdapter extends BaseAdapter {
	private Context c;
	private List<New> listNews;
	private DisplayImageOptions displayImageOptions;// 图片显示配置

	public NewsAdapter(Context c, List<New> listNews) {
		super();
		this.c = c;
		this.listNews = listNews;

		initDisplayImageOptions();
	}

	@Override
	public int getCount() {
		return listNews.size();
	}

	@Override
	public Object getItem(int position) {
		return listNews.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		ViewHolder holder;
		if (v == null) {
			v = LayoutInflater.from(c).inflate(R.layout.item_new, null);
			holder = new ViewHolder();

			holder.newImg = (ImageView) v.findViewById(R.id.new_img);
			holder.newTitle = (TextView) v.findViewById(R.id.new_title);
			holder.newDes = (TextView) v.findViewById(R.id.new_des);

			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}

		New bean = (New) getItem(position);

		// 设置新闻图片
		ImageLoader.getInstance().displayImage(bean.getPicUrl(), holder.newImg,
				displayImageOptions);
		holder.newTitle.setText(bean.getTitle());
		holder.newDes.setText(bean.getDescription());

		return v;
	}

	class ViewHolder {
		ImageView newImg;// 新闻图片
		TextView newTitle, newDes;// 新闻标题，和描述

	}
	
	/**
	 * 2.配制DisplayImageOptions(主要是配制图片在显示过程中的一些参数【图片加载失败，图片地址不存在所显示的默认图片】)
	 */
	private void initDisplayImageOptions() {
		displayImageOptions = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.img_loading_default_big) // 设置图片在下载期间显示的图片
				.showImageForEmptyUri(R.drawable.default_new_img)// 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.img_loading_fail_big) // 设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true)// 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片的缩放类型(目标大小)
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
				.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
				.displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
				.displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
				.build();// 构建完成
	}

}
