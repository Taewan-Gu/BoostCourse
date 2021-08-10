package com.ntscorp.intern.product.repository;

import java.util.List;

import com.ntscorp.intern.product.model.ProductSummary;

public interface ProductRepository {
	public List<ProductSummary> selectAllProductSummaries(Integer start);

	public List<ProductSummary> selectProductSummariesByCategoryId(int categoryId, Integer start);

	public int countAllProductSummaries();

	public int countProductSummariesByCategoryId(int categoryId);
}