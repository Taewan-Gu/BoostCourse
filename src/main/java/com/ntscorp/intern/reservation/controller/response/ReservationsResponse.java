package com.ntscorp.intern.reservation.controller.response;

import java.util.List;

import com.ntscorp.intern.reservation.model.Reservation;
import com.ntscorp.intern.reservation.model.ReservationCount;

public class ReservationsResponse {
	private final List<Reservation> reservations;
	private final ReservationCount reservationCount;

	public ReservationsResponse(List<Reservation> reservations, ReservationCount reservationCount) {
		this.reservations = reservations;
		this.reservationCount = reservationCount;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public ReservationCount getReservationCount() {
		return reservationCount;
	}

	@Override
	public String toString() {
		return "ReservationsResponse [reservations=" + reservations + ", reservationCount=" + reservationCount + "]";
	}
}