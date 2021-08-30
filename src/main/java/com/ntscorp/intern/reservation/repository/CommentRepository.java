package com.ntscorp.intern.reservation.repository;

import java.util.List;

import com.ntscorp.intern.reservation.model.Comment;
import com.ntscorp.intern.reservation.model.CommentsCountAndAverageScore;
import com.ntscorp.intern.reservation.model.FileInfo;
import com.ntscorp.intern.reservation.model.ReservationUserCommentImage;

public interface CommentRepository {
	public List<Comment> selectAllCommentsByDisplayInfoId(int displayInfoId);

	public List<Comment> selectCommentsByDisplayInfoId(int displayInfoId, int limit);

	public CommentsCountAndAverageScore selectCommentsCountAndAverageScore(int displayInfoId);

	public int insertComment(Comment comment);

	public int insertFileInfo(FileInfo fileInfo);

	public int insertReservationUserCommentImage(ReservationUserCommentImage reservationUserCommentImage);
}