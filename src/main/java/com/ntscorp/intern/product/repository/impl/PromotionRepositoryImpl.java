package com.ntscorp.intern.product.repository.impl;

import static com.ntscorp.intern.product.repository.sql.PromotionSql.*;

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
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private RowMapper<Promotion> promotionImageRowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

	public PromotionRepositoryImpl(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Promotion> selectAllPromotions() {
		return namedParameterJdbcTemplate.query(FIND_ALL_PROMOTIONS, promotionImageRowMapper);
	}
}