package com.ntscorp.intern.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntscorp.intern.product.model.ProductPrice;
import com.ntscorp.intern.product.service.ProductService;
import com.ntscorp.intern.reservation.controller.response.ProductReservationResponse;
import com.ntscorp.intern.reservation.model.ProductReservation;
import com.ntscorp.intern.reservation.service.ReservationService;

@RestController
@RequestMapping("/api")
public class ReserveController {
	private static final boolean VALID = false;
	private static final boolean INVALID = true;
	private static final int MIN_DISPLAY_INFO_ID = 1;

	private final ProductService productService;
	private final ReservationService reservationService;

	@Autowired
	public ReserveController(ProductService productService, ReservationService reservationService) {
		this.productService = productService;
		this.reservationService = reservationService;
	}

	@GetMapping("/reserve")
	public ResponseEntity<ProductReservationResponse> getProductReservation(@RequestParam
	int displayInfoId) {
		ProductReservation productReservation = reservationService.getReservationProductByDisplayInfoId(displayInfoId);
		List<ProductPrice> productPrices = productService.getProducePriceByDisplayInfoId(displayInfoId);

		if (isNotValidateDisplayInfoId(displayInfoId)) {
			throw new IllegalArgumentException("arguments = [displayInfoId: " + displayInfoId + "]");
		}

		ProductReservationResponse productReservationResponse = new ProductReservationResponse(productReservation,
			productPrices);

		return ResponseEntity.ok(productReservationResponse);
	}

	private boolean isNotValidateDisplayInfoId(int displayInfoId) {
		if (displayInfoId < MIN_DISPLAY_INFO_ID) {
			return INVALID;
		}
		return VALID;
	}
}