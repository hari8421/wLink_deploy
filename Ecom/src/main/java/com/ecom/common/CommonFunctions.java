package com.ecom.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonFunctions {
	private static final Logger logger = LoggerFactory.getLogger(CommonFunctions.class);

	public static String getCurrentMonthYear() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		String dateTime = formatter.format(date);
		return dateTime;
	}

	public static String getCurrentDateTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dateTime = formatter.format(date);
		return dateTime;
	}

	public static String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateTime = formatter.format(date);
		return dateTime;
	}

	public static String getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String dateTime = formatter.format(date);
		return dateTime;
	}

	public static String getCurrentdate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String dateTime = formatter.format(date);
		return dateTime;
	}

	public static String getCurrentdateformat() {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");
		Date date = new Date();
		String dateTime = formatter.format(date);
		return dateTime;
	}

	public static String getCurrentTimeformat() {
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		Date date = new Date();
		String dateTime = formatter.format(date);
		return dateTime;
	}

	public static String getDBDateToView(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		String dateTime = formatter.format(d);
		return dateTime;
	}

	public static String getDBDateToViewTwo(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		String dateTime = formatter.format(d);
		return dateTime;
	}

	public static String getDBDateToViewThird(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = formatter.format(d);
		return dateTime;
	}

	public static String getDBDateToViewByMonth(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String dateTime = formatter.format(d);
		return dateTime;
	}

	public static String getDBDateToViewByMonthWithString(String sd) {
		Date date1;
		try {
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			date1 = formatter1.parse(sd);
			return date1 + "";
		} catch (ParseException e) {
			return "";
		}

	}

	public static String getDBLastDateOfMonth(String d) {
		String lastDate = "";
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = sdf.parse(d);
			Calendar caldate = Calendar.getInstance();
			caldate.setTime(date);
			int lDate = caldate.getActualMaximum(Calendar.DATE);
			caldate.set(Calendar.DATE, lDate);
			lastDate = sdf.format(caldate.getTime());

		} catch (Exception e) {
			logger.error("Common Method getDBLastDateOfMonth Method Exception-->" + e);
		}

		return lastDate;
	}

	public static String getDBDateToViewByMonthTwo(String d) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String dateTime = null;
		try {
			date = format.parse(d);
			dateTime = formatter.format(date);
		} catch (Exception e) {
			logger.error("Common Method getDBDateToViewByMonthTwo Method Exception-->" + e);
		}

		return dateTime;
	}

	public static String getPrevDate(String indate) {
		String prevDate = "";
		String prevmonth = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = null;
		try {
			parse = dateFormat.parse(indate);
		} catch (Exception e) {
			logger.error("getPrevDate exception :" + e);
		}
		Calendar c = Calendar.getInstance();
		c.setTime(parse);
		c.add(Calendar.DATE, -1);

		int forYear = c.get(Calendar.YEAR);
		int forMonth = c.get(Calendar.MONTH);
		int forMonths = forMonth + 1;
		int forDate = c.get(Calendar.DATE);
		if (forDate < 10) {
			prevDate = "0" + Integer.toString(forDate);
		} else {
			prevDate = Integer.toString(forDate);
		}
		if (forMonths < 10) {
			prevmonth = "0" + Integer.toString(forMonths);
		} else {
			prevmonth = Integer.toString(forMonths);
		}

		String result = forYear + "-" + prevmonth + "-" + prevDate;

		return result;
	}

	public static String getPreviuosDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		Date oneDayBefore = new Date(date.getTime() - (24 * 3600000));

		String result = dateFormat.format(oneDayBefore);
		return result;
	}
}
