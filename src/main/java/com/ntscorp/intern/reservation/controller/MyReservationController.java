package com.ntscorp.intern.reservation.controller;

import java.util.List;

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
	private final ReservationService reservationService;

	@Autowired
	public MyReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/reservations")
	public ResponseEntity<ReservationsResponse> getMyReservations(@RequestParam
	String reservationEmail) {
		List<Reservation> reservations = reservationService.getAllReservationsByEmail(reservationEmail);
		ReservationCount reservationCount = reservationService.getReservationCountByEmail(reservationEmail);
		ReservationsResponse reservationsResponse = new ReservationsResponse(reservations, reservationCount);
		return ResponseEntity.ok(reservationsResponse);
	}

	@PutMapping("/reservations/{reservationInfoId}")
	public ResponseEntity<Integer> changeReservationInfoId(@PathVariable
	int reservationInfoId) {
		return ResponseEntity.ok(reservationService.changeReservationInfoCancelFlag(reservationInfoId));
	}
}
