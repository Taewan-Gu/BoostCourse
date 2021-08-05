package com.ntscorp.intern.product.repository.impl;

import static com.ntscorp.intern.product.repository.sql.PromotionSql.*;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ntscorp.intern.product.model.PromotionImage;
import com.ntscorp.intern.product.repository.PromotionRepository;

@Repository
public class PromotionRepositoryImpl implements PromotionRepository {
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private RowMapper<PromotionImage> promotionImageRowMapper = BeanPropertyRowMapper.newInstance(PromotionImage.class);

	public PromotionRepositoryImpl(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<PromotionImage> findAllPromotionImages() {
		return namedParameterJdbcTemplate.query(FIND_ALL_PROMOTION_IMAGES, promotionImageRowMapper);
	}
}