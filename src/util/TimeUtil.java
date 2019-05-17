package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	private static final String WHOLE_DATA_FORMAT="yyyy-MM-dd HH:mm:ss";
	
	public static String intToStrWholeDate(Long time){
		if(time!=null && time!=0){
			Date d=new Date(time*1000L);
			SimpleDateFormat sfd=new SimpleDateFormat(WHOLE_DATA_FORMAT);
			return sfd.format(d);
		}
		return "";
	}

}
