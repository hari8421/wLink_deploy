package com.ecom.otp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ecom.common.CommonFunctions;

public class OTPHandler {
	private static final Logger logger = LoggerFactory.getLogger(OTPHandler.class);
	public static void sentCustomerMessage(String mobileNo, String vendorId, String requestFor, JdbcTemplate jdbcObj) {
		try {

			String getVendQ = "select VENDOR_NAME from vendor_branch_details where VENDOR_ID=? ";
			String vendorName = jdbcObj.queryForObject(getVendQ, new Object[] { vendorId }, String.class);

			
			String apiKey = "apikey=" + "hP7EJCmq8f4-518QmrUKxOWMsLIeiZcBhlM6B1ubhS";
			String message = generateMessage(null, requestFor, vendorName,null);
			String sender = "&sender=TXTLCL";
			String numbers = "&numbers=" + mobileNo;
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			logger.info("reponse " + stringBuffer.toString());
			logger.info("OTP Sent");

			

		} catch (Exception Ex) {
			logger.error("Error with sending message" + Ex);
		}
	}
	public static void OTPMessager(String mobileNo, String vendorId, String requestFor, JdbcTemplate jdbcObj) {
		try {

			String getVendQ = "select VENDOR_NAME from vendor_branch_details where VENDOR_ID=? ";
			String vendorName = jdbcObj.queryForObject(getVendQ, new Object[] { vendorId }, String.class);

			String otp = generateOTP();
			String apiKey = "apikey=" + "hP7EJCmq8f4-518QmrUKxOWMsLIeiZcBhlM6B1ubhS";
			String message = generateMessage(otp, requestFor, vendorName,null);
			String sender = "&sender=TXTLCL";
			String numbers = "&numbers=" + mobileNo;
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			logger.info("reponse " + stringBuffer.toString());
			logger.info("OTP Sent");

			String updateOTP = "update customer_otp_details set IS_ACTIVE =? where CONTACT_NUMBER=? and VENDOR_ID =? and EXPIRES_DATETIME=? and IS_ACTIVE =?";
			jdbcObj.update(updateOTP, "0", mobileNo, vendorId, CommonFunctions.getCurrentDateTime(), "1");

			logger.info("updated the older OTP");

			String insertLatestOtp = "INSERT INTO `customer_otp_details` (`CONTACT_NUMBER`, `OTP`, `CREATED_DATETIME`, `IS_ACTIVE`, `VENDOR_ID`) VALUES (?, ?, ?, ?, ?);";
			jdbcObj.update(insertLatestOtp, mobileNo, otp, CommonFunctions.getCurrentDateTime(), "1", vendorId);
			logger.info("new OTP generated");

		} catch (Exception Ex) {
			logger.error("Error with sending message" + Ex);
		}
	}

	public static int latestOTPValidator(String mobileNo, String OTP, String vendorId, JdbcTemplate jdbcObj) {

		String countQ = "select count(*) from customer_otp_details where CONTACT_NUMBER=? and OTP=? and VENDOR_ID=?";
		return jdbcObj.queryForObject(countQ, new Object[] { mobileNo, OTP, vendorId }, Integer.class);

	}

	private static String generateOTP() {
		int randomPin = (int) (Math.random() * 9000) + 1000;
		String otp = String.valueOf(randomPin);
		return otp;
	}

	private static String generateMessage(String otp, String requestFor, String vendorName,String orderMessage) {
		String message = "";
		if (requestFor.equalsIgnoreCase("login")) {

			message = "&message=" + "Dear customer, Your One Time Password for validation is " + otp
					+ ".Use this password to Login into " + vendorName + " online services";

		} else if (requestFor.equalsIgnoreCase("signup")) {
			message = "&message=" + "Dear customer, Thank you for intiating registration with " + vendorName
					+ " online services." + "Your One Time Password for validation is " + otp
					+ ".Use this password to complete registration";
		}else if(requestFor.contains("OrderConfirm")) {
			message = "&message=" + "Order Placed: Dear customer, Your order with " + vendorName
					+ " containing " +requestFor.split("\\|")[1]+ " with Order ID  " + requestFor.split("\\|")[2]
					+ " has been received,We will deliver the order soon.Happy shopping with http://ansarstores.wlinkstudios.com";
		}
		return message;
	}

}
