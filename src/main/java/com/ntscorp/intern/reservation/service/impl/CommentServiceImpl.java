package com.ntscorp.intern.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntscorp.intern.reservation.model.Comment;
import com.ntscorp.intern.reservation.model.CommentsCountAndAverageScore;
import com.ntscorp.intern.reservation.model.FileInfo;
import com.ntscorp.intern.reservation.repository.CommentRepository;
import com.ntscorp.intern.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	private static final int COMMENTS_LIMIT = 3;
	private static final String HIDED_EMAIL_PART = "****";

	private final CommentRepository commentRepository;

	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	@Override
	public List<Comment> getAllCommentsByDisplayInfoId(int displayInfoId) {
		List<Comment> comments = commentRepository.selectAllCommentsByDisplayInfoId(displayInfoId);
		hideCommentEmails(comments);

		return comments;
	}

	@Override
	public List<Comment> getCommentsByDisplayInfoId(int displayInfoId) {
		List<Comment> comments = commentRepository.selectCommentsByDisplayInfoId(displayInfoId, COMMENTS_LIMIT);
		hideCommentEmails(comments);

		return comments;
	}

	@Override
	public CommentsCountAndAverageScore getCommentsCountAndAverageScore(int displayInfoId) {
		return commentRepository.selectCommentsCountAndAverageScore(displayInfoId);
	}

	@Override
	public int saveCommentWithImage(Comment comment, FileInfo fileInfo) {
		return commentRepository.insertComment(comment);
	}

	@Override
	public int saveComment(Comment comment) {
		return commentRepository.insertComment(comment);
	}

	// 스펙 상 이메일은 ID는 4자리 이상 보장됨.
	private void hideCommentEmails(List<Comment> comments) {
		for (Comment comment : comments) {
			String commentReservationEmail = comment.getReservationEmail();
			comment.setReservationEmail(commentReservationEmail.substring(0, 4) + HIDED_EMAIL_PART);
		}
	}
}