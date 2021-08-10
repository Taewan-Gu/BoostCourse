package com.ntscorp.intern.reservation.controller.response;

import java.util.List;

import com.ntscorp.intern.reservation.model.Comment;

public class CommentsResponse {
	private final List<Comment> comments;

	public CommentsResponse(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Comment> getComments() {
		return comments;
	}

	@Override
	public String toString() {
		return "CommentsResponse [comments=" + comments + "]";
	}
}