package racom.be.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public final static long NUM_100NS_INTERVALS_SINCE_UUID_EPOCH = 0x01b21dd213814000L;
	public final static SimpleDateFormat COUNTER_DATE_FORMAT = new SimpleDateFormat("yyyy_MM_dd");
	public final static SimpleDateFormat HOURLY_REPORT_FORMAT = new SimpleDateFormat("yyyy_MM_dd_HH");

	public static Calendar getHourlyReportDate(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(HOURLY_REPORT_FORMAT.parse(date));
		return calendar;
	}
	public static String getHourlyReportStringDate(Calendar calendar) throws ParseException {
		String format = HOURLY_REPORT_FORMAT.format(getZeroTimeHour(calendar));		
		return format;
	}
	
	public static Date getZeroTimeHour(Calendar calendar) {
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static void main(String[] args) {
		play.Logger.info(getCurrentUUID());
	}

	/*
	 * Terence : get currrent UUID
	 */
	public static String getCurrentUUID() {
		return String.valueOf(Calendar.getInstance().getTimeInMillis());
		// return
		// String.valueOf(TimeUUIDUtils.getUniqueTimeUUIDinMillis().timestamp());
	}

	public static String getTimeForCounterDate(Calendar calendar) {
		String format = COUNTER_DATE_FORMAT.format(calendar.getTime());
		return format;
	}

	public static Date convertTimeUUIDToDate(String stringTimeUUID) {
		long timeSt = Long.parseLong(stringTimeUUID);
		timeSt = (timeSt - DateUtils.NUM_100NS_INTERVALS_SINCE_UUID_EPOCH) / 10;
		play.Logger.info("test " + timeSt);
		return new Date(timeSt);
	}

	public static String convertCalendarToMicroSecond(Calendar time) {
		if (time == null) {
			return "";
		}
		return String.valueOf(time.getTimeInMillis());
	}

	// //////////// Use in new project /////////////////////

	public static String convertDateToSave(Date date) {
		if (date == null) {
			date = Calendar.getInstance().getTime();
		}
		return String.valueOf(date.getTime());
	}

	public static String getCurrentDateToSave() {
		return convertDateToSave(null);
	}

	public static String convertDateToKeytime(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		if (date == null) {
			date = Calendar.getInstance().getTime();
		}
		return formatter.format(date);
	}

	public static String convertDateToShortKeytime(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		if (date == null) {
			date = Calendar.getInstance().getTime();
		}
		return formatter.format(date);
	}

	public static String convertDateToMonth(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		if (date == null) {
			date = Calendar.getInstance().getTime();
		}
		return formatter.format(date);
	}

	

	public static Date getZeroTimeDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static int compareDate(Date date1, Date date2) {
		date1 = getZeroTimeDate(date1);
		date2 = getZeroTimeDate(date2);
		return date1.compareTo(date2);
	}

	public static boolean checkValidDateString(String date) {
		String pattern = "[0-9]{4}/[0-9]{2}/[0-9]{2}";
		if (date == null || "".equals(date))
			return false;
		return date.matches(pattern);
	}

	public static Calendar getZeroTimeInTime(Calendar calendar, int time) {
		calendar.add(Calendar.HOUR_OF_DAY, -time);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar;
	}

	public static Calendar convertStringToCalendar(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date parse;
		try {
			parse = dateFormat.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(parse);
			return calendar;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isValidDate(String date) {
		if (date == null)
			return false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		if (date.trim().length() != dateFormat.toPattern().length())
			return false;
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(date.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	public static String getCurrentDateByFormat(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	public static String daysBetween(Date d1, Date d2) {
		long milisecond = d2.getTime() - d1.getTime();
		long second = (milisecond / 1000);
		long hourN = second / 3600;
		long minuteN = (second % 3600) / 60;
		long secondN = second - (hourN * 3600 + minuteN * 60);
		String result = hourN + ";" + minuteN + ":" + secondN;
		return result;
	}

}
