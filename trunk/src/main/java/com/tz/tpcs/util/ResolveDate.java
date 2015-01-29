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
public class ResolveDate {

	public static Date StringToDate(HttpServletRequest request, String date) {
		Date d = null;
		if (null != date || !date.equals("")) {
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
