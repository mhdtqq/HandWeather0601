package com.fourdworks.handweather.http;

import java.net.URLEncoder;

/**
 * 功能:封装了所有的网络请求 作者:mike 时间：2015-11-17 上午9:07:03 修改:
 */
public class HttpFactory {
	/**
	 * 查询推荐新闻
	 * 
	 * @param responce
	 *            服务器返回接口对象
	 * @param num
	 *            返回数量，最大50
	 * @param page
	 *            分页：页码
	 * @param requestFlag
	 *            请求标记 1:新闻推荐 2.新闻列表
	 */
	public static void searchNewsTJ(ServiceResponce responce, int num,
			int page, int requestFlag) {
		// 1.创建请求参数对象
		RequestParams params = new RequestParams();
		// 2.设置请求参数
		if (requestFlag == 1) {
			params.setHttpUrl(HttpUrls.URL_GETNEWSTJ);// 设置地址
		} else {
			params.setHttpUrl(HttpUrls.URL_GETNEWSLIST);// 设置地址
		}
		
		// 拼接请求参数
		String httpParams = "num=" + num + "&page=" + page;

		params.setHttpParam(httpParams);// 设置参数

		// 设置apikey
		params.setApiKey("26d9d2aaf6f115e406c3971b7c7e65e6");

		// 设置服务器应答接口对象
		params.setResponce(responce);

		// 设置请求标记
		params.setRequestFlag(requestFlag);

		// 3.创建请求对象
		HttpCore core = new HttpCore();

		// 4.执行请求
		core.execute(params);
	}

}
