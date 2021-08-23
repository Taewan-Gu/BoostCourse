package com.ntscorp.intern.reservation.model;

import java.time.LocalDateTime;

public class Reservation {
	private int id;
	private int displayInfoId;
	private String title;
	private LocalDateTime reservationDate;
	private String placeStreet;
	private int totalPrice;
	private int cancel_flag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}

	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getCancel_flag() {
		return cancel_flag;
	}

	public void setCancel_flag(int cancel_flag) {
		this.cancel_flag = cancel_flag;
	}

	@Override
	public String toString() {
		return "MyReservationInfo [id=" + id + ", displayInfoId=" + displayInfoId + ", title=" + title
			+ ", reservationDate=" + reservationDate + ", placeStreet=" + placeStreet + ", totalPrice=" + totalPrice
			+ ", cancel_flag=" + cancel_flag + "]";
	}
}