package com.ntscorp.intern.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ntscorp.intern.product.service.ProductService;
import com.ntscorp.intern.reservation.controller.request.ReserveRequest;
import com.ntscorp.intern.reservation.controller.response.ReserveResponse;
import com.ntscorp.intern.reservation.service.ReservationService;

@Controller
@RequestMapping("/api")
public class ReserveController {
	private static final boolean VALID = false;
	private static final boolean INVALID = true;
	private static final int MIN_DISPLAY_INFO_ID = 1;

	private final ReservationService reservationService;

	@Autowired
	public ReserveController(ProductService productService, ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/reserve")
	public ResponseEntity<ReserveResponse> getProductReservation(@RequestParam
	int displayInfoId) {
		if (isNotValidateDisplayInfoId(displayInfoId)) {
			throw new IllegalArgumentException("arguments = [displayInfoId: " + displayInfoId + "]");
		}

		return ResponseEntity.ok(reservationService.getReserveResponse(displayInfoId));
	}

	@PostMapping(value = "/reserve", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String postReservation(ReserveRequest reservationRequest) {
		reservationService.saveReservation(reservationRequest);
		return "redirect:/";
	}

	private boolean isNotValidateDisplayInfoId(int displayInfoId) {
		if (displayInfoId < MIN_DISPLAY_INFO_ID) {
			return INVALID;
		}
		return VALID;
	}
}