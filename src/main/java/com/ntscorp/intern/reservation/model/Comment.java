package com.ntscorp.intern.reservation.model;

import java.sql.Date;
import java.util.Optional;

public class Comment {
	private int id;
	private int score;
	private String comment;
	private Date reservationDate;
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

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
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