package com.ntscorp.intern.product.service;

import java.util.List;

import com.ntscorp.intern.product.model.ProductDescription;
import com.ntscorp.intern.product.model.ProductPrice;
import com.ntscorp.intern.product.model.ProductSummary;

public interface ProductService {
	public List<ProductSummary> getAllProductSummaries(int start);

	public List<ProductSummary> getProductSummariesByCategoryId(int categoryId, int start);

	public int countAllProductSummaries();

	public int countProductSummariesByCategoryId(int categoryId);

	public ProductDescription getProductDescriptionByDisplayInfoId(int displayInfoId);

	public List<String> getProductImageUrlsByDisplayInfoId(int displayInfoId);

	public List<ProductPrice> getProducePriceByDisplayInfoId(int displayInfoId);
}