package com.fourdworks.handweather.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.fourdworks.handweather.MyApplaction;
import com.fourdworks.handweather.R;

/**
 * 功能:Ui的工具类 作者:mike 时间：2015-11-6 上午8:47:35 修改:
 */
public class UiHelper {

	/**
	 * 创建对话框
	 * 
	 * @param message
	 *            消息 默认值：正在加载
	 * @param isCanCancell
	 *            是否可以取消掉
	 * @return
	 */
	public static Dialog createrDialog(Context c, String message,
			boolean isCanCancell) {
		// 1.创建对话框对象
		Dialog dialog = new Dialog(c, R.style.dialog_loading);

		// 2.设置对话框内容区域的视图
		// -----
		// 2.1创建视图对象
		View dialogView = LayoutInflater.from(c).inflate(
				R.layout.view_publicloading, null);
		// 2.2设置对话框内容区域的视图
		dialog.setContentView(dialogView);

		// 3.为视图绑定数据
		// ------
		// 3.0
		ImageView loadImg = (ImageView) dialogView.findViewById(R.id.load_img);
		TextView loadMsg = (TextView) dialogView.findViewById(R.id.load_msg);

		// 3.1为ImageView添加并启动旋转动画
		RotateAnimation rotateAnim = (RotateAnimation) AnimationUtils
				.loadAnimation(c, R.anim.anim_loading);

		// 3.2启动旋转动画
		loadImg.startAnimation(rotateAnim);

		// 3.3设置标题
		if (!TextUtils.isEmpty(message)) {
			loadMsg.setText(message);
		}

		// 3.4是否可以取消掉
		dialog.setCancelable(isCanCancell);

		// 4.返回对话框
		return dialog;
	}

}
