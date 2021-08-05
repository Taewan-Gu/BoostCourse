package com.ntscorp.intern.product.controller.response;

import java.util.List;

import com.ntscorp.intern.product.model.Category;

public class CategoriesResponse {
	private List<Category> Categories;

	public List<Category> getCategories() {
		return Categories;
	}

	public void setCategories(List<Category> categories) {
		Categories = categories;
	}

	@Override
	public String toString() {
		return "CategoriesResponse [Categories=" + Categories + "]";
	}
}