package com.ntscorp.intern.reservation.service;

import java.util.List;

import com.ntscorp.intern.reservation.model.Comment;

public interface CommentService {
	public List<Comment> selectAllComments(int displayInfoId);

	public List<Comment> selectCommentsLimitThree(int displayInfoId);
}