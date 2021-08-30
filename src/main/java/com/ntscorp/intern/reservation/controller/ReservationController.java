package com.ntscorp.intern.reservation.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntscorp.intern.common.utils.ValidationUtils;
import com.ntscorp.intern.reservation.controller.response.ReservationsResponse;
import com.ntscorp.intern.reservation.model.Reservation;
import com.ntscorp.intern.reservation.model.ReservationCount;
import com.ntscorp.intern.reservation.model.ReservationInfo;
import com.ntscorp.intern.reservation.service.ReservationService;

@RestController
@RequestMapping("/api")
public class ReservationController {
	private static final int NO_CHANGE_CANCEL_FLAG = 0;

	private final ReservationService reservationService;

	@Autowired
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/reservations")
	public ResponseEntity<?> getMyReservations(@RequestParam
	String reservationEmail, HttpSession session) {
		if (ValidationUtils.isNotValidatedLoginEmail(reservationEmail, session.getAttribute("email").toString())) {
			return new ResponseEntity<>("Email Incorrect", HttpStatus.UNAUTHORIZED);
		}

		if (ValidationUtils.isNotVaildatedEmail(reservationEmail)) {
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
		if (ValidationUtils.isNotValidatedReservationInfoId(reservationInfoId)) {
			throw new IllegalArgumentException("arguments = [reservationInfoId: " + reservationInfoId + "]");
		}

		ReservationInfo reservationInfo = reservationService.getReservationInfoById(reservationInfoId);

		if (ValidationUtils.isNotValidatedLoginEmail(reservationInfo.getReservationEmail(),
			session.getAttribute("email").toString())) {
			return new ResponseEntity<>(NO_CHANGE_CANCEL_FLAG, HttpStatus.UNAUTHORIZED);
		}

		if (isNotValidatedReservationDate(reservationInfo.getReservationDate())) {
			return new ResponseEntity<>(NO_CHANGE_CANCEL_FLAG, HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(reservationService.changeReservationInfoCancelFlag(reservationInfoId));
	}

	private boolean isNotValidatedReservationDate(LocalDateTime reservationDate) {
		Timestamp reservationDateTimestamp = Timestamp.valueOf(reservationDate);
		Timestamp nowTimestamp = Timestamp.valueOf(LocalDateTime.now());

		if (reservationDateTimestamp.getTime() < nowTimestamp.getTime()) {
			return true;
		}

		return false;
	}
}
