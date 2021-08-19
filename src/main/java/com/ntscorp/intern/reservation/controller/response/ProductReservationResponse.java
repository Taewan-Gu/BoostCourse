package com.ntscorp.intern.reservation.controller.response;

import java.util.List;

import com.ntscorp.intern.product.model.ProductPrice;
import com.ntscorp.intern.reservation.model.ProductReservation;

public class ProductReservationResponse {
	private final ProductReservation productReservation;
	private final List<ProductPrice> productPrices;

	public ProductReservationResponse(ProductReservation productReservation, List<ProductPrice> productPrices) {
		this.productReservation = productReservation;
		this.productPrices = productPrices;
	}

	public ProductReservation getProductReservation() {
		return productReservation;
	}

	public List<ProductPrice> getProductPrices() {
		return productPrices;
	}

	@Override
	public String toString() {
		return "ProductReservationResponse [productReservation=" + productReservation + ", productPrices="
			+ productPrices
			+ "]";
	}
}