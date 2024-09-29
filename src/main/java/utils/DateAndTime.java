package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateAndTime {

	/**
	 * Get the Current Time
	 * 
	 * @return String
	 */
	public static String getTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("kk.mm");
		return dateFormat.format(date);
	}

	/**
	 * Get the Current Date
	 * 
	 * @return String
	 */
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		return dateFormat.format(date);
	}

	/**
	 * Get date by passing days
	 * 
	 * @param dateFormat
	 * @param days
	 * @return String
	 * 
	 */
	public static String getDate(String dateFormat, int days) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		Date date = cal.getTime();
		return formatter.format(date);
	}

	/**
	 * Get the formatted date
	 * 
	 * @param date
	 * @param format
	 * @return String
	 * 
	 */
	public static String getFormattedDate(String date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
			Date parsedDate = sdf.parse(date);
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			return formatter.format(parsedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Get DateTime as String to use in filename
	 * 
	 * @return String
	 */
	public static String getDateTimeName() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * This method get formatted date without adding time zone
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getFormattedWithoutAddingTimeZone(String date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			Date parsedDate = sdf.parse(date);
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.format(parsedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Get DateTime in format of User Import Page
	 * 
	 * @return String
	 */
	public static String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, hh:mm a");
		Date date = new Date();
		return dateFormat.format(date);
	}
}