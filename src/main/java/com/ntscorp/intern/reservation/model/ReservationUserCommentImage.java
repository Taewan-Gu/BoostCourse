package com.ntscorp.intern.reservation.model;

public class ReservationUserCommentImage {
	private int reservationInfoId;
	private int reservationUserCommentId;
	private int fileId;

	public ReservationUserCommentImage() {}

	public ReservationUserCommentImage(int reservationInfoId, int reservationUserCommentId, int fileId) {
		this.reservationInfoId = reservationInfoId;
		this.reservationUserCommentId = reservationUserCommentId;
		this.fileId = fileId;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getReservationUserCommentId() {
		return reservationUserCommentId;
	}

	public void setReservationUserCommentId(int reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	@Override
	public String toString() {
		return "ReservationUserCommentImage [reservationInfoId=" + reservationInfoId + ", reservationUserCommentId="
			+ reservationUserCommentId + ", fileId=" + fileId + "]";
	}
}