package com.ntscorp.intern.product.repository;

import java.util.List;

import com.ntscorp.intern.product.model.ProductDescription;
import com.ntscorp.intern.product.model.ProductImage;
import com.ntscorp.intern.product.model.ProductPrice;
import com.ntscorp.intern.product.model.ProductSummary;

public interface ProductRepository {
	public List<ProductSummary> selectAllProductSummaries(int start);

	public List<ProductSummary> selectProductSummariesByCategoryId(int categoryId, int start);

	public int countAllProductSummaries();

	public int countProductSummariesByCategoryId(int categoryId);

	public ProductDescription selectProductDescriptionByDisplayInfoId(int displayInfoId);

	public List<ProductImage> selectProductImagesByDisplayInfoId(int displayInfoId, int limit);

	public List<ProductPrice> selectProductPricesByDisplayInfoId(int displayInfoId);

	public ProductSummary selectProductSummaryByDisplayInfoId(int displayInfoId);
}