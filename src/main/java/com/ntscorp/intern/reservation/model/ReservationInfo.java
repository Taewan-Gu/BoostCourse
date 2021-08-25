package com.ntscorp.intern.reservation.model;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ReservationInfo {
	private int productId;
	private int displayInfoId;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;
	private LocalDateTime reservationDate;
	private int cancelFlag;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;

	public ReservationInfo() {}

	public ReservationInfo(Reservation reservation) {
		ZoneId zoneId = ZoneId.of("Asia/Seoul");
		this.productId = reservation.getProductId();
		this.displayInfoId = reservation.getDisplayInfoId();
		this.reservationName = reservation.getReservationName();
		this.reservationTel = reservation.getReservationTel();
		this.reservationEmail = reservation.getReservationEmail();
		this.reservationDate = reservation.getReservationDate();
		LocalDateTime currentDateTime = LocalDateTime.now(zoneId);
		this.createDate = currentDateTime;
		this.modifyDate = currentDateTime;
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

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

	public int getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(int cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "ReservationInfo [productId=" + productId + ", displayInfoId=" + displayInfoId + ", reservationName="
			+ reservationName + ", reservationTel=" + reservationTel + ", reservationEmail=" + reservationEmail
			+ ", reservationDate=" + reservationDate + ", cancelFlag=" + cancelFlag + ", createDate=" + createDate
			+ ", modifyDate=" + modifyDate + "]";
	}
}