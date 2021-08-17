package com.ntscorp.intern.reservation.controller.response;

import java.util.List;

import com.ntscorp.intern.reservation.model.Comment;
import com.ntscorp.intern.reservation.model.CommentsCountAndAverageScore;

public class CommentsResponse {
	private final List<Comment> comments;
	private final CommentsCountAndAverageScore commentsCountAndAverageScore;

	public CommentsResponse(List<Comment> comments, CommentsCountAndAverageScore commentsCountAndAverageScore) {
		this.comments = comments;
		this.commentsCountAndAverageScore = commentsCountAndAverageScore;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public CommentsCountAndAverageScore getCommentsCountAndAverageScore() {
		return commentsCountAndAverageScore;
	}

	@Override
	public String toString() {
		return "CommentsResponse [comments=" + comments + ", commentsCountAndAverageScore="
			+ commentsCountAndAverageScore + "]";
	}
}