package com.ntscorp.intern.reservation.service;

import com.ntscorp.intern.reservation.model.ProductReservation;

public interface ReservationService {
	public ProductReservation getReservationProductByDisplayInfoId(int displayInfoId);
}