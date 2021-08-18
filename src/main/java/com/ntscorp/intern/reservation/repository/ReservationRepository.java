package com.ntscorp.intern.reservation.repository;

import com.ntscorp.intern.reservation.model.ProductReservation;

public interface ReservationRepository {
	public ProductReservation selectReservaionProductByDisplayInfoId(int displayInfoId);
}