package com.ntscorp.intern.reservation.repository;

import java.util.List;

import com.ntscorp.intern.reservation.model.Comment;
import com.ntscorp.intern.reservation.model.CommentsCountAndAverageScore;

public interface CommentRepository {
	public List<Comment> selectAllCommentsByDisplayInfoId(int displayInfoId);

	public List<Comment> selectCommentsByDisplayInfoId(int displayInfoId, int limit);

	public CommentsCountAndAverageScore selectCommentsCountAndAverageScore(int displayInfoId);
}