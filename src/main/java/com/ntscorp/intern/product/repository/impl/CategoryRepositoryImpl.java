package com.ntscorp.intern.product.repository.impl;

import static com.ntscorp.intern.product.repository.sql.CategorySql.SELECT_ALL_CATEGORIES;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ntscorp.intern.product.model.Category;
import com.ntscorp.intern.product.repository.CategoryRepository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final RowMapper<Category> categoryRowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	public CategoryRepositoryImpl(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Category> selectAllCategories() {
		return namedParameterJdbcTemplate.query(SELECT_ALL_CATEGORIES, categoryRowMapper);
	}
}