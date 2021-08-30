package com.ntscorp.intern.reservation.controller.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ntscorp.intern.product.model.ProductPrice;
import com.ntscorp.intern.product.model.ProductSummary;

public class ReserveResponse {
	private final ProductSummary productSummary;
	private final List<ProductPrice> productPrices;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
	private final LocalDateTime reservationDate;

	public ReserveResponse(ProductSummary productSummary, List<ProductPrice> productPrices,
		LocalDateTime reservationDate) {
		this.productSummary = productSummary;
		this.productPrices = productPrices;
		this.reservationDate = reservationDate;
	}

	public ProductSummary getProductSummary() {
		return productSummary;
	}

	public List<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	@Override
	public String toString() {
		return "ReserveResponse [productSummary=" + productSummary + ", productPrices=" + productPrices
			+ ", reservationDate=" + reservationDate + "]";
	}
}