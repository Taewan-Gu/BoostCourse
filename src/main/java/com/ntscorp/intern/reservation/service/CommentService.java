package com.ntscorp.intern.reservation.service;

import java.util.List;

import com.ntscorp.intern.reservation.model.Comment;
import com.ntscorp.intern.reservation.model.CommentsCountAndAverageScore;
import com.ntscorp.intern.reservation.model.FileInfo;

public interface CommentService {
	public List<Comment> getAllCommentsByDisplayInfoId(int displayInfoId);

	public List<Comment> getCommentsByDisplayInfoId(int displayInfoId);

	public CommentsCountAndAverageScore getCommentsCountAndAverageScore(int displayInfoId);

	public int saveCommentWithImage(Comment comment, FileInfo fileInfo);

	public int saveComment(Comment comment);
}