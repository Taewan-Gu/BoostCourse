package com.ntscorp.intern.reservation.model;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Comment {
	private int id;
	private int score;
	private String comment;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.", timezone = "Asia/Seoul")
	private LocalDateTime reservationDate;
	private String reservationEmail;
	private Optional<String> commentImageUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "Comment [id=" + id + ", score=" + score + ", comment=" + comment + ", reservationDate="
			+ reservationDate + ", reservationEmail=" + reservationEmail + ", commentImageUrl=" + commentImageUrl + "]";
	}
}