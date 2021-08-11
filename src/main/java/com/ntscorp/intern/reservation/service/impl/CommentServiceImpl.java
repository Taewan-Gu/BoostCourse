package com.ntscorp.intern.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntscorp.intern.reservation.model.Comment;
import com.ntscorp.intern.reservation.model.CommentsCountAndAverageScore;
import com.ntscorp.intern.reservation.repository.CommentRepository;
import com.ntscorp.intern.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;

	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	@Override
	public List<Comment> selectAllComments(int displayInfoId) {
		List<Comment> comments = commentRepository.selectAllComments(displayInfoId);
		for (Comment comment : comments) {
			String commentReservationEmail = comment.getReservationEmail();
			comment.setReservationEmail(commentReservationEmail.substring(0, 4));
		}

		return comments;
	}

	@Override
	public List<Comment> selectCommentsLimitThree(int displayInfoId) {
		List<Comment> comments = commentRepository.selectCommentsLimitThree(displayInfoId);
		for (Comment comment : comments) {
			String commentReservationEmail = comment.getReservationEmail();
			comment.setReservationEmail(commentReservationEmail.substring(0, 4));
		}

		return comments;
	}

	@Override
	public CommentsCountAndAverageScore selectCommentsCountAndAverageScore(int displayInfoId) {
		CommentsCountAndAverageScore commentsCountAndAverageScore = commentRepository
			.selectCommentsCountAndAverageScore(displayInfoId);

		if (commentsCountAndAverageScore.getAverageScore() == null) {
			commentsCountAndAverageScore.setAverageScore(new Float(0));
		}

		return commentsCountAndAverageScore;
	}
}