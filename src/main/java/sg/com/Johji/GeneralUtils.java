package sg.com.Johji;

public class GeneralUtils {

	public static boolean isHeroku() {
		return System.getProperty("os.name").indexOf("Windows") == -1;
	}
	
	public static String getContextRoot() {
		return isHeroku() ? "" : "/McCheck";
	}
	
//	only 7:00 am - 9:05 am, 12pm - 15:05pm
	public static boolean isOpen() {
//		if (!isHeroku()) {
			return true;
//		}
//		Calendar cal = Calendar.getInstance();
//		// Convert Calendar to LocalTime
//		Instant instant = Instant.ofEpochMilli(cal.getTimeInMillis());
//		LocalTime now = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Singapore")).toLocalTime();
//		
//		return (now.isAfter(LocalTime.of(7, 0)) && now.isBefore(LocalTime.of(9, 5))) ||
//				(now.isAfter(LocalTime.of(12, 0)) && now.isBefore(LocalTime.of(15, 5)));
		
	}
}
