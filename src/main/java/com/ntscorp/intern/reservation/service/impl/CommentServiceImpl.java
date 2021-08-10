package com.ntscorp.intern.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntscorp.intern.reservation.model.Comment;
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
		return commentRepository.selectAllComments(displayInfoId);
	}

	@Override
	public List<Comment> selectCommentsLimitThree(int displayInfoId) {
		return commentRepository.selectCommentsLimitThree(displayInfoId);
	}
}