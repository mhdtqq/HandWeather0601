package com.fourdworks.handweather.db;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fourdworks.handweather.MyApplaction;
import com.fourdworks.handweather.beans.UserCity;

/**
 * 功能:本程序的数据库 作者:mike 时间：2015-10-30 上午9:04:30 修改:
 */
public class HandWeatherDAO extends SQLiteOpenHelper {
	private static String dbName = "handWeather";// 数据库的名字
	private static int version = 2;// 数据库当前版本
	// 单例
	private static HandWeatherDAO instance;
	private static Integer lock = 1;

	// 用户城市表
	private String userCityTableName = "usercity";// 用户城市表名
	private String createUserCity = "CREATE TABLE " + "" + userCityTableName
			+ " " + "(city varchar(20) primary key,lowtemp varchar(10),"
			+ "hightemp varchar(10),weatherinfo varchar(15),"
			+ "statue varchar(1) default 1)";// 创建用户表语句
	private String dropUserCity = "DROP TABLE IF EXISTS " + userCityTableName;// 删除用户城市表

	/**
	 * 创建数据库
	 */
	private HandWeatherDAO() {
		super(MyApplaction.getInstance(), dbName, null, version);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 创建：创建数据表
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// 创建用户城市表
		db.execSQL(createUserCity);
	}

	/**
	 * 数据库版本更新（当版本号变化的时候，自动执行）
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// 删除原有表
		db.execSQL(dropUserCity);
		// 创建新表
		onCreate(db);
	}

	/**
	 * 获取单例
	 * 
	 * @return
	 */
	public static HandWeatherDAO getInstance() {
		if (instance == null) {
			synchronized (lock) {
				return instance == null ? instance = new HandWeatherDAO()
						: instance;
			}
		}
		return instance;
	}

	/**
	 * 查询所有用户城市(查)
	 * 
	 * @return 用户城市集合
	 */
	public List<UserCity> getAllUserCities() {
		List<UserCity> listUserCities = new ArrayList<UserCity>();
		// 1.Sql
		String sql = "SELECT * FROM " + userCityTableName;
		// 2.获取数据库操作对象
		SQLiteDatabase db = getReadableDatabase();
		// 3.执行sql语句
		Cursor cursor = db.rawQuery(sql, null);
		// 4.DAO
		while (cursor.moveToNext()) {
			String city = cursor.getString(cursor.getColumnIndex("city"));// 城市名字:武汉
			String lowtemp = cursor.getString(cursor.getColumnIndex("lowtemp"));// 最低温:13℃
			String hightemp = cursor.getString(cursor
					.getColumnIndex("hightemp"));// 最高温:38℃
			String weatherinfo = cursor.getString(cursor
					.getColumnIndex("weatherinfo"));// 天气信息:小雨
			String statue = cursor.getString(cursor.getColumnIndex("statue"));// 状态:0(未选中)
																				// 1（选中：默认值）
			// 添加到集合

			listUserCities.add(new UserCity(city, lowtemp, hightemp,
					weatherinfo, statue));
		}
		return listUserCities;
	}

	/**
	 * 添加一个用户城市(增)
	 * 
	 * @param city
	 *            :需要被添加的城市
	 */
	public void addUserCity(UserCity city) {
		try {
			String sql = "INSERT INTO " + userCityTableName
					+ " VALUES(?,?,?,?,?)";
			// 2.执行sql语句
			SQLiteDatabase dbWraite = getWritableDatabase();
			dbWraite.execSQL(
					sql,
					new String[] { city.getCity(), city.getLowtemp(),
							city.getHightemp(), city.getWeatherinfo(),
							city.getStatue() });
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 通过城市名字删除一个用户城市（删）
	 * 
	 * @param cityName
	 *            ： 城市名字
	 */
	public void deleteUserCity(String cityName) {
		// 删除语句
		String sql = "DELETE FROM " + userCityTableName + " where city like ?";
		// 执行sql
		getWritableDatabase().execSQL(sql, new String[] { cityName + "%" });
	}
	
	/**
	 * 判断城市是否存在
	 * 
	 * @param cityName
	 *            城市名
	 * @return true:存在 false:不存在
	 */
	public boolean isCityExists(String cityName) {
		// 1.Sql
		String sql = "SELECT * FROM " + userCityTableName
				+ " WHERE city LIKE ?";
		// 2.获取数据库操作对象
		SQLiteDatabase db = getReadableDatabase();
		// 3.执行sql语句
		Cursor cursor = db.rawQuery(sql, new String[] { cityName + "%" });
		// 4.DAO
		if (cursor.moveToNext()) {
			return true;
		}

		return false;
	}

	/**
	 * 更新对应城市天气信息
	 * 
	 * @param city
	 */
	public void updateWeatherInfo(UserCity city) {
		String sql = "UPDATE "
				+ userCityTableName
				+ " SET lowtemp = ?,hightemp = ?,weatherinfo = ? where city like ?";

		getWritableDatabase().execSQL(
				sql,
				new String[] { city.getLowtemp(), city.getHightemp(),
						city.getWeatherinfo(), city.getCity_() });
	}

	/**
	 * 更新对应城市状态信息
	 * 
	 * @param cityName
	 *            城市名字
	 * @param statue
	 *            状态
	 */
	public void updateStatue(String cityName, String statue) {
		String sql = "UPDATE " + userCityTableName
				+ " SET statue = ? WHERE city LIKE ?";

		getWritableDatabase().execSQL(sql,
				new String[] { statue, cityName + "%" });
	}

	/**
	 * 获取当前用户表有多少个城市
	 * 
	 * @return
	 */
	public int getUserCitySize() {
		String sql = "SELECT COUNT(*) FROM " + userCityTableName;

		Cursor cursor = getWritableDatabase().rawQuery(sql, null);
		cursor.moveToNext();

		return cursor.getInt(0);
	}

}
