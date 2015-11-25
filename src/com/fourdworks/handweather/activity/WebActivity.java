package com.fourdworks.handweather.activity;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fourdworks.handweather.R;
import com.fourdworks.handweather.beans.New;
import com.fourdworks.handweather.global.CostantValue;
import com.fourdworks.handweather.utils.LogUtils;

/**
 * 功能:显示网页 作者:mike 时间：2015-11-23 上午11:34:02 修改:
 */
public class WebActivity extends BaseActivity {
	private String TAG = "WebActivity";

	private WebView webView;
	private String webUrl;

	public WebActivity() {
		super(R.layout.activity_web);
	}

	@Override
	protected void initView() {
		webView = (WebView) findViewById(R.id.show_web);

	}

	@Override
	protected void initData() {
		New news = (New) getIntent().getSerializableExtra(
				CostantValue.KEY_INTENT);
		LogUtils.d(TAG, news.toString());
		webUrl = news.getUrl();
	}

	@Override
	protected void bindView() {
		webView.loadUrl(webUrl);
		// 设置网页解析的客户端，如果不设置，会默认使用本机默认的浏览器打开
		webView.setWebViewClient(new WebViewClient());
	}

}
