package com.ntscorp.intern.product.repository.impl;

import static com.ntscorp.intern.product.repository.sql.PromotionSql.SELECT_ALL_PROMOTIONS;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ntscorp.intern.product.model.Promotion;
import com.ntscorp.intern.product.repository.PromotionRepository;

@Repository
public class PromotionRepositoryImpl implements PromotionRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final RowMapper<Promotion> promotionImageRowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

	public PromotionRepositoryImpl(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Promotion> selectAllPromotions() {
		return namedParameterJdbcTemplate.query(SELECT_ALL_PROMOTIONS, promotionImageRowMapper);
	}
}