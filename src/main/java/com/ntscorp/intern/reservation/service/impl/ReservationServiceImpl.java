package com.ntscorp.intern.reservation.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntscorp.intern.product.model.ProductPrice;
import com.ntscorp.intern.product.model.ProductSummary;
import com.ntscorp.intern.product.service.ProductService;
import com.ntscorp.intern.reservation.controller.request.ProductPriceRequest;
import com.ntscorp.intern.reservation.controller.request.ReservationRequest;
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
	public void saveReservation(ReservationRequest reserveRequest) {
		int reservationInfoKey = saveReservationInfo(reserveRequest);

		saveReservationInfoPrices(reserveRequest, reservationInfoKey);
	}

	@Transactional
	@Override
	public int changeReservationInfoCancelFlag(int reservationInfoId) {
		return reservationRepository.updateReservationInfoCancelFlag(CANCEL_FLAG, reservationInfoId);
	}

	private int saveReservationInfo(ReservationRequest reserveRequest) {
		String rawReservationDate = reserveRequest.getReservationDate();
		LocalDateTime reservationDate = LocalDate.parse(rawReservationDate).atTime(LocalTime.now());
		ReservationInfo reservationInfo = new ReservationInfo(
			reserveRequest.getProductId(),
			reserveRequest.getDisplayInfoId(),
			reserveRequest.getReservationName(),
			reserveRequest.getReservationTel(),
			reserveRequest.getReservationEmail(),
			reservationDate);

		return reservationRepository.insertReservationInfo(reservationInfo);
	}

	private void saveReservationInfoPrices(ReservationRequest reserveRequest, int reservationInfoKey) {
		List<ReservationInfoPrice> reservationInfoPrices = new ArrayList<>();

		List<ProductPriceRequest> productPriceRequests = reserveRequest.getProductPrices();
		productPriceRequests.forEach(productPriceRequest -> {
			ReservationInfoPrice reservationInfoPrice = new ReservationInfoPrice();
			int ticketCount = productPriceRequest.getCount();
			if (ticketCount == TICKET_ZERO) {
				return;
			}

			reservationInfoPrice.setReservationInfoId(reservationInfoKey);
			reservationInfoPrice.setProductPriceId(productPriceRequest.getProductPriceId());
			reservationInfoPrice.setCount(ticketCount);
			reservationInfoPrices.add(reservationInfoPrice);
		});

		reservationInfoPrices.forEach(reservationInfoPrice -> {
			reservationRepository.insertReservationInfoPrice(reservationInfoPrice);
		});
	}

	private LocalDateTime getRandomReservationDate() {
		Random rawRandomNumber = new Random();
		int randomNumber = 1 + rawRandomNumber.nextInt(4);

		LocalDateTime reservationDate = LocalDateTime.now();
		return reservationDate.plusDays(randomNumber);
	}
}