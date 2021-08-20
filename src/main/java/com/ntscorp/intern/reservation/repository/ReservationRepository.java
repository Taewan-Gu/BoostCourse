package com.ntscorp.intern.reservation.repository;

import com.ntscorp.intern.reservation.model.ReservationInfo;
import com.ntscorp.intern.reservation.model.ReservationInfoPrice;

public interface ReservationRepository {
	public int insertReservationInfo(ReservationInfo reservationInfo);

	public int insertReservationInfoPrice(ReservationInfoPrice reservationInfoPrice);
}