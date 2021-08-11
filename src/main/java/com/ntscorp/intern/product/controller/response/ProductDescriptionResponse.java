package com.ntscorp.intern.product.controller.response;

import java.util.List;

import com.ntscorp.intern.product.model.ProductDescription;

public class ProductDescriptionResponse {
	private final ProductDescription productDescription;
	private final List<String> productImageUrls;

	public ProductDescriptionResponse(ProductDescription productDescription, List<String> productImageUrls) {
		this.productDescription = productDescription;
		this.productImageUrls = productImageUrls;
	}

	public ProductDescription getProductDescription() {
		return productDescription;
	}

	public List<String> getProductImageUrls() {
		return productImageUrls;
	}

	@Override
	public String toString() {
		return "ProductDescriptionResponse [productDescription=" + productDescription + ", productImageUrls="
			+ productImageUrls + "]";
	}
}