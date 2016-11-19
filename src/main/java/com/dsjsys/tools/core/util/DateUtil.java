package com.dsjsys.tools.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static Date parse(String strDate){
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = fmt.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
		
	}

}
