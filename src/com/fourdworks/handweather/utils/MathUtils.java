package com.fourdworks.handweather.utils;

import java.util.Random;

/**
 * 功能:数学工具类
 * 作者:mike
 * 时间：2015-11-20 上午10:44:17
 * 修改:
 */
public class MathUtils {
	/**
	 * 随机某一区间的数值[min,max]
	 * @param min
	 *       最小数
	 * @param max
	 * 		最大数	
	 * @return
	 */
	public static final int getRandomBetweenRange(int min,int max){
		return new Random().nextInt(max-min+1)+min;
	}
	
	
	
	
}
