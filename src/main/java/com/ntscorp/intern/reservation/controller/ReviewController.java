package com.ntscorp.intern.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntscorp.intern.reservation.controller.response.CommentsResponse;
import com.ntscorp.intern.reservation.model.Comment;
import com.ntscorp.intern.reservation.model.CommentsCountAndAverageScore;
import com.ntscorp.intern.reservation.service.CommentService;

@RestController
@RequestMapping("/api")
public class ReviewController {
	private static final boolean VALID = false;
	private static final boolean INVALID = true;
	private static final int MIN_DISPLAY_INFO_ID = 1;

	private final CommentService commentService;

	@Autowired
	public ReviewController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping("/comments/all")
	public ResponseEntity<CommentsResponse> getAllComments(@RequestParam
	int displayInfoId) {
		List<Comment> comments = commentService.getAllCommentsByDisplayInfoId(displayInfoId);
		CommentsCountAndAverageScore commentsCountAndAverageScore = commentService
			.getCommentsCountAndAverageScore(displayInfoId);

		if (isNotValidateDisplayInfoId(displayInfoId)) {
			throw new IllegalArgumentException("arguments = [displayInfoId: " + displayInfoId + "]");
		}

		CommentsResponse commentsResponse = new CommentsResponse(comments, commentsCountAndAverageScore);

		return ResponseEntity.ok(commentsResponse);
	}

	@GetMapping("/comments")
	public ResponseEntity<CommentsResponse> getComments(@RequestParam
	int displayInfoId) {
		List<Comment> comments = commentService.getCommentsByDisplayInfoId(displayInfoId);
		CommentsCountAndAverageScore commentsCountAndAverageScore = commentService
			.getCommentsCountAndAverageScore(displayInfoId);

		if (isNotValidateDisplayInfoId(displayInfoId)) {
			throw new IllegalArgumentException("arguments = [displayInfoId: " + displayInfoId + "]");
		}

		CommentsResponse commentsResponse = new CommentsResponse(comments, commentsCountAndAverageScore);

		return ResponseEntity.ok(commentsResponse);
	}

	private boolean isNotValidateDisplayInfoId(int displayInfoId) {
		if (displayInfoId < MIN_DISPLAY_INFO_ID) {
			return INVALID;
		}
		return VALID;
	}
}