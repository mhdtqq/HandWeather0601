package com.fourdworks.handweather.db;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.fourdworks.handweather.MyApplaction;
import com.fourdworks.handweather.beans.City;
import com.fourdworks.handweather.global.CostantValue;
import com.fourdworks.handweather.utils.FileUtils;

/**
 * 功能:全国所有城市的操作类：如果本app任何地方需要访问全国城市这张表，都需要通过本类操作 作者:mike 时间：2015-10-28 上午9:49:11
 * 修改:
 */
public class AllCityDao {
	private String TAG = "AllCityDao";
	// -------------
	
	private SQLiteDatabase db;// 数据库操作类
	private String dbName = "city.db";// 数据量的名字
	private String allCityName = "city";// 全国城市表名
	private String dbAssetsPath = "db/city.db";// 数据库在Assets中存在的路径
	private String dbSdcardPath = Environment.getDataDirectory()
			+ File.separator + "data" + File.separator
			+ CostantValue.APPPACKAGENAME + File.separator + dbName;// 数据库在Sdcard中存在的路径

	private static Integer loc = 1;

	// 单例
	// 声明一个空的本类静态对象-----------------------------------------
	private static AllCityDao instance;

	// 私有化本类构造函数-----------------------------------------
	private AllCityDao() {
		// 实例化db
		// 1.判断city.db是不是在本app对应的包中,如果不存在
		if (!FileUtils.isFileExists(dbSdcardPath)) {
			// false:
			// 1.将city.db拷贝到本app对应的包中
			FileUtils.copyAssetsToSdcard(dbAssetsPath, dbSdcardPath);
		}

		// 2.实例化db
		db = MyApplaction.getInstance().openOrCreateDatabase(dbSdcardPath,
				Context.MODE_PRIVATE, null);
	}

	// 1 2 3
	// 刚好进入 刚好进入 刚好进入

	/**
	 * 安全：可以唯一保证本对象是单一实例 相对高效:只有第一次调用该方法会进入互斥锁，以后都不会进入 节省内存:用的时候分配内存
	 * 
	 * @return
	 */

	// 唯一入口：静态的共有的
	public static AllCityDao getInstance() {
		if (instance == null) {
			synchronized (loc) {
				return instance == null ? instance = new AllCityDao()
						: instance;
			}
		}
		return instance;
	}
	
	// -------------------------------------------------
	/**
	 * 查询所有城市
	 * 
	 * @return
	 */
	public List<City> getAllCity() {
		List<City> listCities = new ArrayList<City>();
		// 查询所用记录的语句
		String sql = "SELECT * FROM " + allCityName;
		// 查询所有记录
		Cursor cursor = db.rawQuery(sql, null);
		// 循环读取
		while (cursor.moveToNext()) {
			// 添加到集合中
			listCities.add(cursorToCity(cursor));
		}

		return listCities;
	}

	// 防注入
	/**
	 * 查询满足条件的城市
	 * 
	 * @param sellection
	 *            条件(城市名字、全拼、全拼首字母,首字母)
	 * @return
	 */
	public List<City> searchCity(String sellection) {
		List<City> listCities = new ArrayList<City>();

		String sql = "select * from "
				+ allCityName
				+ " where city like ? or allpy like ? or allfirstpy like ? or firstpy like ?";

		sellection = sellection+"%";
			
		Cursor cursor = db.rawQuery(sql, new String[] { sellection, sellection,
				sellection, sellection });
		
		// 循环读取
		while (cursor.moveToNext()) {
			// 添加到集合中
			listCities.add(cursorToCity(cursor));
		}
		
		return listCities;
	}
	
	/**
	 * 将Cursor封装成City对象
	 * 
	 * @param cursor
	 * @return
	 */
	private City cursorToCity(Cursor cursor) {
		String _id = cursor.getString(cursor.getColumnIndex("_id"));// 获取id
		String province = cursor.getString(cursor.getColumnIndex("province"));// 获取省
		String city = cursor.getString(cursor.getColumnIndex("city"));// 获取市名
		String allpy = cursor.getString(cursor.getColumnIndex("allpy"));// 获取全拼
		String number = cursor.getString(cursor.getColumnIndex("number"));// 获取邮编
		String allfirstpy = cursor.getString(cursor
				.getColumnIndex("allfirstpy"));// 获取首拼音
		String firstpy = cursor.getString(cursor.getColumnIndex("firstpy"));// 获取首字母
		return new City(_id, province, city, allpy, number, allfirstpy, firstpy);
	}

}
