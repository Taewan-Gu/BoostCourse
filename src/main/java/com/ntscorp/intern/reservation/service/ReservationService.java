package com.ntscorp.intern.reservation.service;

import com.ntscorp.intern.reservation.controller.request.ReserveRequest;
import com.ntscorp.intern.reservation.controller.response.ReserveResponse;

public interface ReservationService {
	public void saveReservation(ReserveRequest reservationRequest);

	public ReserveResponse getReserveResponse(int displayInfoId);
}