package com.ntscorp.intern.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntscorp.intern.reservation.model.ProductReservation;
import com.ntscorp.intern.reservation.repository.ReservationRepository;
import com.ntscorp.intern.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	private final ReservationRepository reservationRepository;

	@Autowired
	public ReservationServiceImpl(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Override
	public ProductReservation getReservationProductByDisplayInfoId(int displayInfoId) {
		return reservationRepository.selectReservaionProductByDisplayInfoId(displayInfoId);
	}
}