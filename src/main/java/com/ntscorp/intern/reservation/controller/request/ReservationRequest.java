package com.ntscorp.intern.reservation.controller.request;

import java.util.List;

public class ReservationRequest {
	private int productId;
	private int displayInfoId;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;
	private String reservationDate;
	private List<ProductPriceRequest> productPrices;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTel() {
		return reservationTel;
	}

	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public List<ProductPriceRequest> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(List<ProductPriceRequest> productPrices) {
		this.productPrices = productPrices;
	}

	@Override
	public String toString() {
		return "ReservationRequest [productId=" + productId + ", displayInfoId=" + displayInfoId + ", reservationName="
			+ reservationName + ", reservationTel=" + reservationTel + ", reservationEmail=" + reservationEmail
			+ ", reservationDate=" + reservationDate + ", productPrices=" + productPrices + "]";
	}
}