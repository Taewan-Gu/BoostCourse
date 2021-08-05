package com.ntscorp.intern.product.repository;

import java.util.List;

import com.ntscorp.intern.product.model.Category;

public interface CategoryRepository {
	public List<Category> findAllCategories();
}