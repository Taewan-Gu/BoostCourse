package com.ntscorp.intern.reservation.repository;

import java.util.List;

import com.ntscorp.intern.reservation.model.Comment;
import com.ntscorp.intern.reservation.model.CommentsCountAndAverageScore;

public interface CommentRepository {
	public List<Comment> selectAllComments(int displayInfoId);

	public List<Comment> selectCommentsLimitThree(int displayInfoId);

	public CommentsCountAndAverageScore selectCommentsCountAndAverageScore(int displayInfoId);
}