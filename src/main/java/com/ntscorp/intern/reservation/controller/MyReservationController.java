package com.ntscorp.intern.reservation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ntscorp.intern.common.Validation;
import com.ntscorp.intern.reservation.controller.response.ReservationsResponse;
import com.ntscorp.intern.reservation.model.Reservation;
import com.ntscorp.intern.reservation.model.ReservationCount;
import com.ntscorp.intern.reservation.model.ReservationInfo;
import com.ntscorp.intern.reservation.service.ReservationService;

@Controller
@RequestMapping("/api")
public class MyReservationController {
	private static final int NO_CHANGE_CANCEL_FLAG = 0;

	private final ReservationService reservationService;
	private final Validation validation = new Validation();

	@Autowired
	public MyReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/reservations")
	public ResponseEntity<?> getMyReservations(@RequestParam
	String reservationEmail, HttpSession session) {
		if (validation.isNotValidatedLoginEmail(reservationEmail, session.getAttribute("email").toString())) {
			return new ResponseEntity<>("Email Incorrect", HttpStatus.UNAUTHORIZED);
		}

		if (validation.isNotVaildatedEmail(reservationEmail)) {
			throw new IllegalArgumentException("arguments = [reservationEmail: " + reservationEmail + "]");
		}

		List<Reservation> reservations = reservationService.getAllReservationsByEmail(reservationEmail);
		ReservationCount reservationCount = reservationService.getReservationCountByEmail(reservationEmail);
		ReservationsResponse reservationsResponse = new ReservationsResponse(reservations, reservationCount);
		return ResponseEntity.ok(reservationsResponse);
	}

	@PutMapping("/reservations/{reservationInfoId}")
	public ResponseEntity<Integer> changeReservationInfoId(@PathVariable
	int reservationInfoId, HttpSession session) {
		if (validation.isNotValidatedReservationInfoId(reservationInfoId)) {
			throw new IllegalArgumentException("arguments = [reservationInfoId: " + reservationInfoId + "]");
		}

		ReservationInfo reservationInfo = reservationService.getReservationInfoById(reservationInfoId);

		if (validation.isNotValidatedLoginEmail(reservationInfo.getReservationEmail(),
			session.getAttribute("email").toString())) {
			return new ResponseEntity<>(NO_CHANGE_CANCEL_FLAG, HttpStatus.UNAUTHORIZED);
		}

		if (validation.isNotValidatedReservationDate(reservationInfo.getReservationDate())) {
			return new ResponseEntity<>(NO_CHANGE_CANCEL_FLAG, HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(reservationService.changeReservationInfoCancelFlag(reservationInfoId));
	}
}
