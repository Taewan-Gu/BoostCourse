package com.ntscorp.intern.reservation.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Reservation {
	private int id;
	private int productId;
	private int displayInfoId;
	private String title;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime reservationDate;
	private String placeStreet;
	private int totalPrice;
	private int cancelFlag;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;
	private List<ReservationInfoPrice> reservationInfoPrices;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public int getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(int cancelFlag) {
		this.cancelFlag = cancelFlag;
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

	public List<ReservationInfoPrice> getReservationInfoPrices() {
		return reservationInfoPrices;
	}

	public void setReservationInfoPrices(List<ReservationInfoPrice> reservationInfoPrices) {
		this.reservationInfoPrices = reservationInfoPrices;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", productId=" + productId + ", displayInfoId=" + displayInfoId + ", title="
			+ title + ", reservationDate=" + reservationDate + ", placeStreet=" + placeStreet + ", totalPrice="
			+ totalPrice + ", cancelFlag=" + cancelFlag + ", reservationName=" + reservationName + ", reservationTel="
			+ reservationTel + ", reservationEmail=" + reservationEmail + ", reservationInfoPrices="
			+ reservationInfoPrices + "]";
	}
}