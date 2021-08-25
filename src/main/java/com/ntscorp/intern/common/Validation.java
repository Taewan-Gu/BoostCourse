package com.ntscorp.intern.common;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.ntscorp.intern.reservation.model.Reservation;
import com.ntscorp.intern.reservation.model.ReservationInfoPrice;

public class Validation {
	private static final int MIN_RESERVATION_INFO_ID = 1;
	private static final int MIN_DISPLAY_INFO_ID = 1;
	private static final int MIN_CATEGORY_ID = 1;
	private static final int MIN_START = 0;
	private static final int MIN_PRODUCT_ID = 1;
	private static final int MIN_PRODUCT_PRICE_ID = 1;
	private static final int MIN_PRODUCT_PRICE_TOTAL_COUNT = 1;
	private static final int MIN_PRODUCT_PRICE_COUNT = 0;
	private static final String EMAIL_REGEX_PATTERN = "^[0-9a-zA-Z]{4,20}@[0-9a-zA-Z]{1,100}[.][0-9a-zA-Z]{2,10}$";
	private static final String TEL_REGEX_PATTERN = "^010-[0-9]{4}-[0-9]{4}$|^01[1789]-[0-9]{3}-[0-9]{4}$";

	public boolean isNotVaildatedEmail(String email) {
		String regexValidation = "^[0-9a-zA-Z]{4,20}@[0-9a-zA-Z]{1,100}[.][0-9a-zA-Z]{2,10}$";
		if (Pattern.matches(regexValidation, email)) {
			return false;
		}
		return true;
	}

	public boolean isNotValidatedReservationInfoId(int reservationInfoId) {
		if (reservationInfoId < MIN_RESERVATION_INFO_ID) {
			return true;
		}
		return false;
	}

	public boolean isNotValidatedLoginEmail(String reservationEmail, String loginEmail) {
		if (reservationEmail.equals(loginEmail)) {
			return false;
		}
		return true;
	}

	public boolean isNotValidatedDisplayInfoId(int displayInfoId) {
		if (displayInfoId < MIN_DISPLAY_INFO_ID) {
			return true;
		}
		return false;
	}

	public boolean isNotValidatedProducts(int start) {
		if (start < MIN_START) {
			return true;
		}
		return false;
	}

	public boolean isNotValidatedProducts(int categoryId, int start) {
		if (categoryId < MIN_CATEGORY_ID || start < MIN_START) {
			return true;
		}
		return false;
	}

	public boolean isNotValidatedReserveRequest(Reservation reservation) {
		if (reservation.getDisplayInfoId() < MIN_DISPLAY_INFO_ID) {
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

		if (Pattern.matches(EMAIL_REGEX_PATTERN, reservation.getReservationEmail()) == false) {
			return true;
		}

		if (Pattern.matches(TEL_REGEX_PATTERN, reservation.getReservationTel()) == false) {
			return true;
		}

		return false;
	}

	public boolean isNotValidatedReservationDate(LocalDateTime reservationDate) {
		Timestamp reservationDateTimestamp = Timestamp.valueOf(reservationDate);
		Timestamp nowTimestamp = Timestamp.valueOf(LocalDateTime.now());

		if (reservationDateTimestamp.getTime() < nowTimestamp.getTime()) {
			return true;
		}

		return false;
	}
}