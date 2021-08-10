package com.ntscorp.intern.product.controller.response;

import java.util.List;

import com.ntscorp.intern.product.model.ProductDescription;
import com.ntscorp.intern.product.model.ProductImage;

public class ProductDescriptionResponse {
	private final ProductDescription productDescription;
	private final List<ProductImage> productImages;

	public ProductDescriptionResponse(ProductDescription productDescription, List<ProductImage> productImages) {
		this.productDescription = productDescription;
		this.productImages = productImages;
	}

	public ProductDescription getProductDescription() {
		return productDescription;
	}

	public List<ProductImage> getProductImages() {
		return productImages;
	}

	@Override
	public String toString() {
		return "ProductDescriptionResponse [productDescription=" + productDescription + ", productImages="
			+ productImages + "]";
	}
}