package com.ntscorp.intern.reservation.service;

import java.util.List;

import com.ntscorp.intern.reservation.controller.response.ReserveResponse;
import com.ntscorp.intern.reservation.model.Reservation;
import com.ntscorp.intern.reservation.model.ReservationCount;
import com.ntscorp.intern.reservation.model.ReservationInfo;

public interface ReservationService {
	public void saveReservation(Reservation reservation);

	public ReserveResponse getReserveResponse(int displayInfoId);

	public List<Reservation> getAllReservationsByEmail(String reservationEmail);

	public ReservationCount getReservationCountByEmail(String reservationEmail);

	public int changeReservationInfoCancelFlag(int reservationInfoId);

	public ReservationInfo getReservationInfoById(int reservationInfoId);
}