package com.ntscorp.intern.product.controller.response;

import java.util.List;

import com.ntscorp.intern.product.model.ProductSummary;

public class ProductsResponse {
	private final int totalCount;
	private final List<ProductSummary> products;

	public ProductsResponse(int totalCount, List<ProductSummary> products) {
		this.totalCount = totalCount;
		this.products = products;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public List<ProductSummary> getProducts() {
		return products;
	}

	@Override
	public String toString() {
		return "ProductsResponse [totalCount=" + totalCount + ", products=" + products + "]";
	}
}