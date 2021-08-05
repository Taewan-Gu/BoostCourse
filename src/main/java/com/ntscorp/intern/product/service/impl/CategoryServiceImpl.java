package com.ntscorp.intern.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntscorp.intern.product.model.Category;
import com.ntscorp.intern.product.repository.CategoryRepository;
import com.ntscorp.intern.product.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Category> findAllCategories() {
		return categoryRepository.findAllCategories();
	}
}