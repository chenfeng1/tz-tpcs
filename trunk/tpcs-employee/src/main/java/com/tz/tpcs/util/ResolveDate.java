package com.tz.tpcs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 * 将从页面接受到的字符串转换成日期类型
 * 
 * @author guan
 * 
 */
public final class ResolveDate {

	/**
	 * 私有的空参
	 */
	private ResolveDate() {
		
	}
	/**
	 * 
	 * @param request
	 * @param date
	 * @return
	 */
	public static Date stringToDate(HttpServletRequest request, String date) {
		Date d = null;
		if (null != date || !("").equals(date)) {
			String ds = request.getParameter(date);
			if (null != ds && ds.trim().length() > 0) {
				try {
					d = new SimpleDateFormat("yyyy-MM-dd").parse(ds);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return d;
	}
}
