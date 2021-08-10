package com.ntscorp.intern.reservation.repository;

import java.util.List;

import com.ntscorp.intern.reservation.model.Comment;

public interface CommentRepository {
	public List<Comment> selectAllComments(int displayInfoId);

	public List<Comment> selectCommentsLimitThree(int displayInfoId);
}