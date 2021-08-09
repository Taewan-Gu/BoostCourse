package com.ntscorp.intern.product.controller.response;

import java.util.List;

import com.ntscorp.intern.product.model.Category;

public class CategoriesResponse {
	private final List<Category> categories;

	public CategoriesResponse(List<Category> categories) {
		this.categories = categories;
	}

	public List<Category> getCategories() {
		return categories;
	}

	@Override
	public String toString() {
		return "CategoriesResponse [categories=" + categories + "]";
	}
}