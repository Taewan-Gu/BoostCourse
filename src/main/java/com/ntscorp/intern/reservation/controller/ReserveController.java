package com.ntscorp.intern.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ntscorp.intern.common.utils.ValidationUtils;
import com.ntscorp.intern.reservation.controller.response.ReserveResponse;
import com.ntscorp.intern.reservation.model.Reservation;
import com.ntscorp.intern.reservation.service.ReservationService;

@Controller
@RequestMapping("/api")
public class ReserveController {
	private final ReservationService reservationService;

	@Autowired
	public ReserveController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/reserve")
	public ResponseEntity<ReserveResponse> getProductReservation(@RequestParam
	int displayInfoId) {

		if (ValidationUtils.isNotValidatedDisplayInfoId(displayInfoId)) {
			throw new IllegalArgumentException("arguments = [displayInfoId: " + displayInfoId + "]");
		}
		return ResponseEntity.ok(reservationService.getReserveResponse(displayInfoId));
	}

	@PostMapping(value = "/reserve")
	public String postReservation(Reservation reservation) {
		if (ValidationUtils.isNotValidatedReserveRequest(reservation)) {
			throw new IllegalArgumentException("arguments = " + reservation);
		}

		reservationService.saveReservation(reservation);
		return "redirect:/";
	}
}