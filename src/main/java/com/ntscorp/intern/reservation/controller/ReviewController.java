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
	private final CommentService commentService;

	@Autowired
	public ReviewController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping("/comments/all")
	public ResponseEntity<CommentsResponse> getComments(@RequestParam
	int displayInfoId) {
		List<Comment> comments = commentService.selectAllComments(displayInfoId);
		CommentsCountAndAverageScore commentsCountAndAverageScore = commentService
			.selectCommentsCountAndAverageScore(displayInfoId);

		CommentsResponse commentsResponse = new CommentsResponse(comments, commentsCountAndAverageScore);

		return ResponseEntity.ok(commentsResponse);
	}

	@GetMapping("/comments")
	public ResponseEntity<CommentsResponse> getThreeComments(@RequestParam
	int displayInfoId) {
		List<Comment> comments = commentService.selectCommentsLimitThree(displayInfoId);
		CommentsCountAndAverageScore commentsCountAndAverageScore = commentService
			.selectCommentsCountAndAverageScore(displayInfoId);

		CommentsResponse commentsResponse = new CommentsResponse(comments, commentsCountAndAverageScore);

		return ResponseEntity.ok(commentsResponse);
	}
}