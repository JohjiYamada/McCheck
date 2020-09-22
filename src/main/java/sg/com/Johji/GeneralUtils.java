package sg.com.Johji;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;

public class GeneralUtils {

	public static boolean isHeroku() {
		return System.getProperty("os.name").indexOf("Windows") == -1;
	}
	
	public static String getContextRoot() {
		return isHeroku() ? "" : "/McCheck";
	}
	
//	only 7:00 am - 9:05 am, 12pm - 15:05pm
	public static boolean isOpen() {
			return true;
//		Calendar cal = Calendar.getInstance();
//		// Convert Calendar to LocalTime
//		Instant instant = Instant.ofEpochMilli(cal.getTimeInMillis());
//		LocalTime now = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Singapore")).toLocalTime();
//		
//		return (now.isAfter(LocalTime.of(7, 0)) && now.isBefore(LocalTime.of(9, 0)));
		
	}
}
