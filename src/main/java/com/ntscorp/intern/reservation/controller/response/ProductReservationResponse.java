package com.ntscorp.intern.reservation.controller.response;

import java.util.List;

import com.ntscorp.intern.product.model.ProductPrice;
import com.ntscorp.intern.reservation.model.ProductReservation;

public class ProductReservationResponse {
	private final ProductReservation productReservation;
	private final List<ProductPrice> productPrice;

	public ProductReservationResponse(ProductReservation productReservation, List<ProductPrice> productPrice) {
		this.productReservation = productReservation;
		this.productPrice = productPrice;
	}

	public ProductReservation getProductReservation() {
		return productReservation;
	}

	public List<ProductPrice> getProductPrice() {
		return productPrice;
	}

	@Override
	public String toString() {
		return "ProductReservationResponse [productReservation=" + productReservation + ", productPrice=" + productPrice
			+ "]";
	}
}