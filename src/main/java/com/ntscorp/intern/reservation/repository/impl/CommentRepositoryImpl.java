package com.ntscorp.intern.reservation.repository.impl;

import static com.ntscorp.intern.reservation.repository.sql.CommentSql.SELECT_ALL_COMMENTS_BY_DISPLAY_INFO_ID;
import static com.ntscorp.intern.reservation.repository.sql.CommentSql.SELECT_COMMENTS_BY_DISPLAY_INFO_ID;
import static com.ntscorp.intern.reservation.repository.sql.CommentSql.SELECT_COMMENTS_COUNT_AND_AVERAGE_SCORE;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.ntscorp.intern.reservation.model.Comment;
import com.ntscorp.intern.reservation.model.CommentsCountAndAverageScore;
import com.ntscorp.intern.reservation.model.FileInfo;
import com.ntscorp.intern.reservation.repository.CommentRepository;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final SimpleJdbcInsert insertCommentAction;
	private final SimpleJdbcInsert insertFileInfoAction;
	private final RowMapper<Comment> commentRowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	private final RowMapper<CommentsCountAndAverageScore> commentsCountAndAverageScoreRowMapper = BeanPropertyRowMapper
		.newInstance(CommentsCountAndAverageScore.class);

	public CommentRepositoryImpl(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.insertCommentAction = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_user_comment").usingGeneratedKeyColumns("id");
		this.insertFileInfoAction = new SimpleJdbcInsert(dataSource)
			.withTableName("file_info").usingGeneratedKeyColumns("id");
	}

	@Override
	public List<Comment> selectAllCommentsByDisplayInfoId(int displayInfoId) {
		Map<String, ?> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return namedParameterJdbcTemplate.query(SELECT_ALL_COMMENTS_BY_DISPLAY_INFO_ID, param, commentRowMapper);
	}

	@Override
	public List<Comment> selectCommentsByDisplayInfoId(int displayInfoId, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		params.put("limit", limit);
		return namedParameterJdbcTemplate.query(SELECT_COMMENTS_BY_DISPLAY_INFO_ID, params, commentRowMapper);
	}

	@Override
	public CommentsCountAndAverageScore selectCommentsCountAndAverageScore(int displayInfoId) {
		Map<String, ?> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return namedParameterJdbcTemplate.queryForObject(SELECT_COMMENTS_COUNT_AND_AVERAGE_SCORE, param,
			commentsCountAndAverageScoreRowMapper);
	}

	@Override
	public int insertComment(Comment comment) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(comment);
		return insertCommentAction.executeAndReturnKey(params).intValue();
	}

	@Override
	public int insertFileInfo(FileInfo fileInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(fileInfo);
		return insertFileInfoAction.executeAndReturnKey(params).intValue();
	}
}