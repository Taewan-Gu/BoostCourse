package com.ntscorp.intern.reservation.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntscorp.intern.reservation.controller.response.ReservationsResponse;
import com.ntscorp.intern.reservation.model.Reservation;
import com.ntscorp.intern.reservation.model.ReservationCount;
import com.ntscorp.intern.reservation.service.ReservationService;

@RestController
@RequestMapping("/api")
public class MyReservationController {
	private static final boolean VALID = false;
	private static final boolean INVALID = true;
	private static final int MIN_RESERVATION_INFO_ID = 1;

	private final ReservationService reservationService;

	@Autowired
	public MyReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/reservations")
	public ResponseEntity<ReservationsResponse> getMyReservations(@RequestParam
	String reservationEmail) {
		if (isNotVaildatedEmail(reservationEmail)) {
			throw new IllegalArgumentException("arguments = [reservationEmail: " + reservationEmail + "]");
		}

		List<Reservation> reservations = reservationService.getAllReservationsByEmail(reservationEmail);
		ReservationCount reservationCount = reservationService.getReservationCountByEmail(reservationEmail);
		ReservationsResponse reservationsResponse = new ReservationsResponse(reservations, reservationCount);
		return ResponseEntity.ok(reservationsResponse);
	}

	@PutMapping("/reservations/{reservationInfoId}")
	public ResponseEntity<Integer> changeReservationInfoId(@PathVariable
	int reservationInfoId) {
		if (isNotValidatedReservationInfoId(reservationInfoId)) {
			throw new IllegalArgumentException("arguments = [reservationInfoId: " + reservationInfoId + "]");
		}

		return ResponseEntity.ok(reservationService.changeReservationInfoCancelFlag(reservationInfoId));
	}

	private boolean isNotVaildatedEmail(String email) {
		String regexValidation = "^[0-9a-zA-Z]{4,20}@[0-9a-zA-Z]{1,100}[.][0-9a-zA-Z]{2,10}$";
		if (Pattern.matches(regexValidation, email)) {
			return VALID;
		}
		return INVALID;
	}

	private boolean isNotValidatedReservationInfoId(int reservationInfoId) {
		if (reservationInfoId < MIN_RESERVATION_INFO_ID) {
			return INVALID;
		}
		return VALID;
	}
}
