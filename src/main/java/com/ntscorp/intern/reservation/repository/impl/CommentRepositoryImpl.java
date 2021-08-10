package com.ntscorp.intern.reservation.repository.impl;

import static com.ntscorp.intern.reservation.repository.sql.CommentSql.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ntscorp.intern.reservation.model.Comment;
import com.ntscorp.intern.reservation.repository.CommentRepository;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final RowMapper<Comment> commentRowMapper = BeanPropertyRowMapper.newInstance(Comment.class);

	public CommentRepositoryImpl(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Comment> selectAllComments(int displayInfoId) {
		Map<String, ?> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return namedParameterJdbcTemplate.query(SELECT_ALL_COMMENTS, param, commentRowMapper);
	}

	@Override
	public List<Comment> selectCommentsLimitThree(int displayInfoId) {
		Map<String, ?> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return namedParameterJdbcTemplate.query(SELECT_COMMENTS_LIMIT_THREE, param, commentRowMapper);
	}

}
