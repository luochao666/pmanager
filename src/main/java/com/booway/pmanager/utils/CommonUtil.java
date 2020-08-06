package com.booway.pmanager.utils;

import java.util.UUID;

/**
 * 公共工具类
 * @author JSB-LUOchao
 *
 */
public class CommonUtil
{
	/**
	 * 获取uuid,作为id使用
	 * @return
	 */
	public static String getUUID()
	{
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	
	public static boolean isEmpty(String str)
	{
		if (str == null || "".equals(str))
		{
			return true;
		}
		return false;
	}
	
	
	
}
