package com.ntscorp.intern.reservation.repository;

import java.util.List;

import com.ntscorp.intern.reservation.model.Reservation;
import com.ntscorp.intern.reservation.model.ReservationCount;
import com.ntscorp.intern.reservation.model.ReservationInfo;
import com.ntscorp.intern.reservation.model.ReservationInfoPrice;

public interface ReservationRepository {
	public int insertReservationInfo(ReservationInfo reservationInfo);

	public void insertReservationInfoPrice(List<ReservationInfoPrice> reservationInfoPrices);

	public List<Reservation> selectAllReservationsByEmail(String reservationEmail);

	public ReservationCount selectReservationCountByEmail(String reservationEmail);

	public int updateReservationInfoCancelFlag(int cancelFlag, int reservationInfoId);

	public ReservationInfo selectReservationInfoById(int reservationInfoId);
}