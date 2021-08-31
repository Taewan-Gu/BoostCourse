package com.ntscorp.intern.common.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.ntscorp.intern.reservation.model.Reservation;
import com.ntscorp.intern.reservation.model.ReservationInfoPrice;

public class ValidationUtils {
	private static final int MIN_RESERVATION_INFO_ID = 1;
	private static final int MIN_DISPLAY_INFO_ID = 1;
	private static final int MIN_CATEGORY_ID = 1;
	private static final int MIN_START = 0;

	private static final int MIN_PRODUCT_ID = 1;
	private static final int MIN_PRODUCT_PRICE_ID = 1;
	private static final int MIN_PRODUCT_PRICE_TOTAL_COUNT = 1;
	private static final int MIN_PRODUCT_PRICE_COUNT = 0;

	private static final int MIN_SCORE = 1;
	private static final int MAX_SCORE = 5;
	private static final int MIN_COMMENT_LENGTH = 5;

	private static final String EMAIL_REGEX_PATTERN = "^[0-9a-zA-Z]{4,20}@[0-9a-zA-Z]{1,100}[.][0-9a-zA-Z]{2,10}$";
	private static final String TEL_REGEX_PATTERN = "^010-[0-9]{4}-[0-9]{4}$|^01[1789]-[0-9]{3}-[0-9]{4}$";

	/**
	 * ValidationUtils.isNotVaildatedEmail("email")    			= true
	 * ValidationUtils.isNotVaildatedEmail("")			    	= true
	 * ValidationUtils.isNotVaildatedEmail(null)    			= true
	 * ValidationUtils.isNotVaildatedEmail("email@gmail.com")   = false
	 */
	public static boolean isNotVaildatedEmail(String email) {
		if (StringUtils.isBlank(email)) {
			return true;
		}

		if (Pattern.matches(EMAIL_REGEX_PATTERN, email)) {
			return false;
		}
		return true;
	}

	/**
	 * ValidationUtils.isNotValidatedReservationInfoId(-2)   	= true
	 * ValidationUtils.isNotValidatedReservationInfoId(0)		= true
	 * ValidationUtils.isNotValidatedReservationInfoId(1)    	= false
	 * ValidationUtils.isNotValidatedReservationInfoId(1000)    = false
	 */
	public static boolean isNotValidatedReservationInfoId(int reservationInfoId) {
		if (reservationInfoId < MIN_RESERVATION_INFO_ID) {
			return true;
		}
		return false;
	}

	/**
	 * ValidationUtils.isNotValidatedLoginEmail(null, null)		= true
	 * ValidationUtils.isNotValidatedLoginEmail("", "")	    	= true
	 * ValidationUtils.isNotValidatedLoginEmail(
	 * 		"emai@asdf.co",
	 * 		"emai@asdf.co")  				 					= false
	 */
	public static boolean isNotValidatedLoginEmail(String reservationEmail, String loginEmail) {
		if (isNotVaildatedEmail(reservationEmail)) {
			return true;
		}

		if (isNotVaildatedEmail(loginEmail)) {
			return true;
		}

		if (reservationEmail.equals(loginEmail)) {
			return false;
		}
		return true;
	}

	/**
	 * ValidationUtils.isNotValidatedDisplayInfoId(-2)   		= true
	 * ValidationUtils.isNotValidatedDisplayInfoId(0)			= true
	 * ValidationUtils.isNotValidatedDisplayInfoId(1)    		= false
	 * ValidationUtils.isNotValidatedDisplayInfoId(1000)    	= false
	 */
	public static boolean isNotValidatedDisplayInfoId(int displayInfoId) {
		if (displayInfoId < MIN_DISPLAY_INFO_ID) {
			return true;
		}
		return false;
	}

	/**
	 * ValidationUtils.isNotValidatedProducts(-2)   			= true
	 * ValidationUtils.isNotValidatedProducts(0)				= false
	 * ValidationUtils.isNotValidatedProducts(1)    			= false
	 * ValidationUtils.isNotValidatedProducts(1000)    			= false
	 */
	public static boolean isNotValidatedProducts(int start) {
		if (start < MIN_START) {
			return true;
		}
		return false;
	}

	/**
	 * ValidationUtils.isNotValidatedProducts(-2, -1)   		= true
	 * ValidationUtils.isNotValidatedProducts(0, 0)				= true
	 * ValidationUtils.isNotValidatedProducts(1, 0)    			= false
	 * ValidationUtils.isNotValidatedProducts(1, 1)    			= false
	 */
	public static boolean isNotValidatedProducts(int categoryId, int start) {
		if (categoryId < MIN_CATEGORY_ID || start < MIN_START) {
			return true;
		}
		return false;
	}

	/**
	 * ValidationUtils.isNotValidatedCommentScore(0)	   		= true
	 * ValidationUtils.isNotValidatedCommentScore(1)			= false
	 * ValidationUtils.isNotValidatedCommentScore(4)    		= false
	 * ValidationUtils.isNotValidatedCommentScore(7)    		= true
	 */
	public static boolean isNotValidatedCommentScore(int score) {
		if (score > MAX_SCORE || score < MIN_SCORE) {
			return true;
		}
		return false;
	}

	/**
	 * ValidationUtils.isNotValidatedComment(null)	   		= true
	 * ValidationUtils.isNotValidatedComment("")			= true
	 * ValidationUtils.isNotValidatedComment("four")		= true
	 * ValidationUtils.isNotValidatedComment("downToFive")	= false
	 */
	public static boolean isNotValidatedComment(String comment) {
		if (StringUtils.isBlank(comment)) {
			return true;
		}

		if (comment.length() < MIN_COMMENT_LENGTH) {
			return true;
		}
		return false;
	}

	public static boolean isNotValidatedReserveRequest(Reservation reservation) {
		if (isNotValidatedDisplayInfoId(reservation.getDisplayInfoId())) {
			return true;
		}

		if (reservation.getProductId() < MIN_PRODUCT_ID) {
			return true;
		}

		int productPriceTotalCount = 0;

		for (ReservationInfoPrice reservationInfoPrice : reservation.getReservationInfoPrices()) {
			if (reservationInfoPrice.getProductPriceId() < MIN_PRODUCT_PRICE_ID) {
				return true;
			}

			if (reservationInfoPrice.getCount() < MIN_PRODUCT_PRICE_COUNT) {
				return true;
			}

			productPriceTotalCount += reservationInfoPrice.getCount();
		}

		if (productPriceTotalCount < MIN_PRODUCT_PRICE_TOTAL_COUNT) {
			return true;
		}

		if (StringUtils.isBlank(reservation.getReservationName())) {
			return true;
		}

		if (isNotVaildatedEmail(reservation.getReservationEmail())) {
			return true;
		}

		if (Pattern.matches(TEL_REGEX_PATTERN, reservation.getReservationTel()) == false) {
			return true;
		}
		return false;
	}
}