package com.ntscorp.intern.product.service;

import java.util.List;

import com.ntscorp.intern.product.model.ProductDescription;
import com.ntscorp.intern.product.model.ProductImage;
import com.ntscorp.intern.product.model.ProductSummary;

public interface ProductService {
	public List<ProductSummary> selectAllProductSummaries(Integer start);

	public List<ProductSummary> selectProductSummariesByCategoryId(int categoryId, Integer start);

	public int countAllProductSummaries();

	public int countProductSummariesByCategoryId(int categoryId);

	public ProductDescription selectProductDescriptionByDisplayInfoId(int displayInfoId);

	public List<ProductImage> selectProductImagesByDisplayInfoId(int displayInfoId);
}