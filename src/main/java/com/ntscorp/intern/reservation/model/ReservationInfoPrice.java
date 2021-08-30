package com.ntscorp.intern.reservation.model;

public class ReservationInfoPrice {
	private int reservationInfoId;
	private int productPriceId;
	private int count;

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ReservationInfoPrice [reservationInfoId=" + reservationInfoId + ", productPriceId=" + productPriceId
			+ ", count=" + count + "]";
	}
}