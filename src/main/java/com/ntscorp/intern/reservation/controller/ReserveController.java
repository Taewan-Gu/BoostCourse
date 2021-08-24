package com.ntscorp.intern.reservation.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ntscorp.intern.reservation.controller.request.ProductPriceRequest;
import com.ntscorp.intern.reservation.controller.request.ReservationRequest;
import com.ntscorp.intern.reservation.controller.response.ReserveResponse;
import com.ntscorp.intern.reservation.service.ReservationService;

@Controller
@RequestMapping("/api")
public class ReserveController {
	private static final boolean VALID = false;
	private static final boolean INVALID = true;
	private static final int MIN_DISPLAY_INFO_ID = 1;
	private static final int MIN_PRODUCT_ID = 1;
	private static final int MIN_PRODUCT_PRICE_ID = 1;
	private static final int MIN_PRODUCT_PRICE_TOTAL_COUNT = 1;
	private static final int MIN_PRODUCT_PRICE_COUNT = 0;

	private final ReservationService reservationService;

	@Autowired
	public ReserveController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/reserve")
	public ResponseEntity<ReserveResponse> getProductReservation(@RequestParam
	int displayInfoId) {

		if (isNotValidatedDisplayInfoId(displayInfoId)) {
			throw new IllegalArgumentException("arguments = [displayInfoId: " + displayInfoId + "]");
		}

		return ResponseEntity.ok(reservationService.getReserveResponse(displayInfoId));
	}

	@PostMapping(value = "/reserve", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String postReservation(ReservationRequest reservationRequest) {
		if (isNotValidatedReserveRequest(reservationRequest)) {
			throw new IllegalArgumentException("arguments = " + reservationRequest);
		}

		reservationService.saveReservation(reservationRequest);
		return "redirect:/";
	}

	private boolean isNotValidatedDisplayInfoId(int displayInfoId) {
		if (displayInfoId < MIN_DISPLAY_INFO_ID) {
			return INVALID;
		}
		return VALID;
	}

	private boolean isNotValidatedReserveRequest(ReservationRequest reservationRequest) {
		if (reservationRequest.getDisplayInfoId() < MIN_DISPLAY_INFO_ID) {
			return INVALID;
		}

		if (reservationRequest.getProductId() < MIN_PRODUCT_ID) {
			return INVALID;
		}

		int productPriceTotalCount = 0;

		List<ProductPriceRequest> productPriceRequests = reservationRequest.getProductPrices();
		for (ProductPriceRequest productPriceRequest : productPriceRequests) {
			if (productPriceRequest.getProductPriceId() < MIN_PRODUCT_PRICE_ID) {
				return INVALID;
			}

			if (productPriceRequest.getProductPriceId() < MIN_PRODUCT_PRICE_COUNT) {
				return INVALID;
			}

			productPriceTotalCount += productPriceRequest.getCount();
		}

		if (productPriceTotalCount < MIN_PRODUCT_PRICE_TOTAL_COUNT) {
			return INVALID;
		}

		if (reservationRequest.getReservationName().isEmpty()) {
			return INVALID;
		}

		String regexEmailValidation = "^[0-9a-zA-Z]{4,20}@[0-9a-zA-Z]{1,100}[.][0-9a-zA-Z]{2,10}$";
		String regexValidationNewTel = "^010-[0-9]{4}-[0-9]{4}$";
		String regexValidationOldTel = "^01[1789]-[0-9]{3}-[0-9]{4}$";
		if (Pattern.matches(regexEmailValidation, reservationRequest.getReservationEmail()) &&
			(Pattern.matches(regexValidationNewTel, reservationRequest.getReservationTel()) ||
				Pattern.matches(regexValidationOldTel, reservationRequest.getReservationTel()))) {
			return VALID;
		}

		return INVALID;
	}
}