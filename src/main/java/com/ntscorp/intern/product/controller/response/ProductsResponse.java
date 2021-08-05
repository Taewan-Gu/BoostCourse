package com.ntscorp.intern.product.controller.response;

import java.util.List;

import com.ntscorp.intern.product.model.ProductSummary;

public class ProductsResponse {
	private int totalCount;
	private List<ProductSummary> products;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<ProductSummary> getProducts() {
		return products;
	}

	public void setProducts(List<ProductSummary> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "ProductsResponse [totalCount=" + totalCount + ", products=" + products + "]";
	}
}