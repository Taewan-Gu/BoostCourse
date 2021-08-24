package com.ntscorp.intern.reservation.service;

import java.util.List;

import com.ntscorp.intern.reservation.controller.request.ReserveRequest;
import com.ntscorp.intern.reservation.controller.response.ReserveResponse;
import com.ntscorp.intern.reservation.model.Reservation;
import com.ntscorp.intern.reservation.model.ReservationCount;

public interface ReservationService {
	public void saveReservation(ReserveRequest reservationRequest);

	public ReserveResponse getReserveResponse(int displayInfoId);

	public List<Reservation> getAllReservationsByEmail(String reservationEmail);

	public ReservationCount getReservationCountByEmail(String reservationEmail);

	public int changeReservationInfoCancelFlag(int reservationInfoId);
}