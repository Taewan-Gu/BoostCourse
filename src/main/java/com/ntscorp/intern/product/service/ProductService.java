package com.ntscorp.intern.product.service;

import java.util.List;

import com.ntscorp.intern.product.model.ProductSummary;

public interface ProductService {
	public List<ProductSummary> findAllProductSummaries(Integer start);

	public List<ProductSummary> findProductSummariesByCategoryId(Integer categoryId, Integer start);

	public int countAllProductSummaries();

	public int countProductSummariesByCategoryId(Integer categoryId);
}