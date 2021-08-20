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
	private boolean cancelFlag;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;

	public ReservationInfo(int productId, int displayInfoId, String reservationName, String reservationTel,
		String reservationEmail, LocalDateTime reservationDate) {
		ZoneId zi = ZoneId.of("Asia/Seoul");
		this.productId = productId;
		this.displayInfoId = displayInfoId;
		this.reservationName = reservationName;
		this.reservationTel = reservationTel;
		this.reservationEmail = reservationEmail;
		this.reservationDate = reservationDate;
		this.createDate = LocalDateTime.now(zi);
		this.modifyDate = LocalDateTime.now(zi);
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

	public boolean isCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(boolean cancelFlag) {
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