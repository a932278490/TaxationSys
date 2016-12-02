package com.dsjsys.tools.core.mapper.util.uuid;

import java.util.Calendar;
import java.util.Date;


/**
 * <p>
 * ID 生成器
 * </p>
 * <p>
 *
 * 
 * @author vurtne
 * @version 1.0 Jul 18, 2011 9:29:54 PM
 * @since JDK 6.0
 */
public class IdUtil {

	/**
	 * 四位随机数与String类型当前日期时分秒毫秒组成的字符串组成的ID,共30位
	 * 
	 * @return Id
	 */

	public static String getId() {

		return (String) new UUIDHexGenerator().generate();

	}

	/**
	 * 随即获得四位随机数
	 * @author Javaing(www.javaing.net)
	 * @return 四位随机数 int
	 */
	public static int getMyRandom() {

		int myRandom = 1;

		while (true) {

			int temp = (int) ((Math.random() *9+1)* 1000);

			if (temp > 0 || temp <= 9) {

				myRandom = temp;

				break;

			}

		}

		return myRandom;

	}
	
	public static long getDatetimeId(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		StringBuffer idStr= new StringBuffer();
		idStr.append(c.get(Calendar.YEAR));
		idStr.append(c.get(Calendar.MONTH)+1);
		idStr.append(c.get(Calendar.DAY_OF_MONTH));
		idStr.append(c.get(Calendar.HOUR_OF_DAY));
		idStr.append(c.get(Calendar.MINUTE));
		idStr.append(c.get(Calendar.SECOND));
		return Long.parseLong(idStr.toString());
	}
}
