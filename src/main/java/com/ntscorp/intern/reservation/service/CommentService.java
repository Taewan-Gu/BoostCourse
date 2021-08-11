package com.ntscorp.intern.reservation.service;

import java.util.List;

import com.ntscorp.intern.reservation.model.Comment;
import com.ntscorp.intern.reservation.model.CommentsCountAndAverageScore;

public interface CommentService {
	public List<Comment> selectAllComments(int displayInfoId);

	public List<Comment> selectCommentsLimitThree(int displayInfoId);

	public CommentsCountAndAverageScore selectCommentsCountAndAverageScore(int displayInfoId);
}