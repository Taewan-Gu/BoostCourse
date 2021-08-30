package com.ntscorp.intern.reservation.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntscorp.intern.product.model.ProductPrice;
import com.ntscorp.intern.product.model.ProductSummary;
import com.ntscorp.intern.product.service.ProductService;
import com.ntscorp.intern.reservation.controller.response.ReserveResponse;
import com.ntscorp.intern.reservation.model.Reservation;
import com.ntscorp.intern.reservation.model.ReservationCount;
import com.ntscorp.intern.reservation.model.ReservationInfo;
import com.ntscorp.intern.reservation.model.ReservationInfoPrice;
import com.ntscorp.intern.reservation.repository.ReservationRepository;
import com.ntscorp.intern.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	private static final int TICKET_ZERO = 0;
	private static final int CANCEL_FLAG = 1;

	private final ReservationRepository reservationRepository;
	private final ProductService productService;

	@Autowired
	public ReservationServiceImpl(ReservationRepository reservationRepository, ProductService productService) {
		this.reservationRepository = reservationRepository;
		this.productService = productService;
	}

	@Override
	public List<Reservation> getAllReservationsByEmail(String reservationEmail) {
		return reservationRepository.selectAllReservationsByEmail(reservationEmail);
	}

	@Override
	public ReservationCount getReservationCountByEmail(String reservationEmail) {
		return reservationRepository.selectReservationCountByEmail(reservationEmail);
	}

	@Override
	public ReserveResponse getReserveResponse(int displayInfoId) {
		ProductSummary productSummary = productService.getProductSummaryByDisplayInfoId(displayInfoId);
		List<ProductPrice> productPrices = productService.getProducePriceByDisplayInfoId(displayInfoId);
		LocalDateTime reservationDate = getRandomReservationDate();

		ReserveResponse reserveResponse = new ReserveResponse(productSummary,
			productPrices, reservationDate);
		return reserveResponse;
	}

	@Transactional
	@Override
	public void saveReservation(Reservation reservation) {
		int reservationInfoKey = saveReservationInfo(reservation);

		saveReservationInfoPrices(reservation.getReservationInfoPrices(), reservationInfoKey);
	}

	@Override
	public int changeReservationInfoCancelFlag(int reservationInfoId) {
		return reservationRepository.updateReservationInfoCancelFlag(CANCEL_FLAG, reservationInfoId);
	}

	@Override
	public ReservationInfo getReservationInfoById(int reservationInfoId) {
		return reservationRepository.selectReservationInfoById(reservationInfoId);
	}

	private int saveReservationInfo(Reservation reservation) {
		ReservationInfo reservationInfo = new ReservationInfo(reservation);

		return reservationRepository.insertReservationInfo(reservationInfo);
	}

	private void saveReservationInfoPrices(List<ReservationInfoPrice> rawReservationInfoPrices,
		int reservationInfoKey) {
		List<ReservationInfoPrice> reservationInfoPrices = new ArrayList<>();
		for (ReservationInfoPrice rawReservationInfoPrice : rawReservationInfoPrices) {
			if (rawReservationInfoPrice.getCount() == TICKET_ZERO) {
				continue;
			}
			rawReservationInfoPrice.setReservationInfoId(reservationInfoKey);
			reservationInfoPrices.add(rawReservationInfoPrice);
		}

		reservationRepository.insertReservationInfoPrice(reservationInfoPrices);
	}

	private LocalDateTime getRandomReservationDate() {
		// 무작위 숫자: 1 ~ 5
		Random rawRandomNumber = new Random();
		int randomNumber = 1 + rawRandomNumber.nextInt(5);

		LocalDateTime reservationDate = LocalDateTime.now();
		return reservationDate.plusDays(randomNumber);
	}
}