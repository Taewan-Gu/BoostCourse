package com.ntscorp.intern.reservation.model;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Comment {
	private int id;
	private int productId;
	private int reservationInfoId;
	private int score;
	private String comment;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.", timezone = "Asia/Seoul")
	private LocalDateTime reservationDate;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private String reservationEmail;
	private Optional<String> commentImageUrl;

	public Comment() {}

	public Comment(int productId, int reservationInfoId, int score, String comment) {
		this.productId = productId;
		this.reservationInfoId = reservationInfoId;
		this.score = score;
		this.comment = comment;
		LocalDateTime currentDateTime = LocalDateTime.now();
		this.createDate = currentDateTime;
		this.modifyDate = currentDateTime;
	}

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

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
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

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public Optional<String> getCommentImageUrl() {
		return commentImageUrl;
	}

	public void setCommentImageUrl(Optional<String> commentImageUrl) {
		this.commentImageUrl = commentImageUrl;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", productId=" + productId + ", reservationInfoId=" + reservationInfoId
			+ ", score=" + score + ", comment=" + comment + ", reservationDate=" + reservationDate + ", createDate="
			+ createDate + ", modifyDate=" + modifyDate + ", reservationEmail=" + reservationEmail
			+ ", commentImageUrl=" + commentImageUrl + "]";
	}
}