package entpay.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy-MM-dd");
	}
	
	public static Date parseTimestamp(String dateStr) {
		return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static Date parseDate(String dateStr, String format) {
		Date date = null;
		
		try {
			date = (new SimpleDateFormat(format)).parse(dateStr);
		} catch (ParseException e) {}
		
		return date;
	}
	
	public static String valueOf(Date date) {
		return valueOf(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String valueOf(Date date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		
		return df.format(date);
	}
	
	public static long timestamp() {
		return System.currentTimeMillis() / 1000L;
	}
	
	public static Date now() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static boolean isAfter(String time) throws ParseException {
		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
		Date cutoff = parser.parse(time);
		Date now = parser.parse(parser.format(new Date()));
	    return now.after(cutoff);
	}
	
	public static Date today() {
		return getRelativeDate(null);
	}

	public static Date tomorrow() {
		return getRelativeDate(1);
	}

	public static Date yesterday() {
		return getRelativeDate(-1);
	}

	private static Date getRelativeDate(Integer day) {
		Calendar cal = Calendar.getInstance();
		if (day != null) {
			cal.add(Calendar.DATE, day);
		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}
