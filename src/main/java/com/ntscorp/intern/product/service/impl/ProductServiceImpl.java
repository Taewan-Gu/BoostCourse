package com.ntscorp.intern.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntscorp.intern.product.model.ProductDescription;
import com.ntscorp.intern.product.model.ProductImage;
import com.ntscorp.intern.product.model.ProductSummary;
import com.ntscorp.intern.product.repository.ProductRepository;
import com.ntscorp.intern.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private static final int PRODUCT_IMAGES_LIMIT = 2;

	private final ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<ProductSummary> getAllProductSummaries(Integer start) {
		List<ProductSummary> productSummaries;

		if (start == null) {
			productSummaries = productRepository.selectAllProductSummaries(0);
		} else {
			productSummaries = productRepository.selectAllProductSummaries(start);
		}

		return productSummaries;
	}

	@Override
	public List<ProductSummary> getProductSummariesByCategoryId(int categoryId, Integer start) {
		List<ProductSummary> productSummaries;

		if (start == null) {
			productSummaries = productRepository.selectProductSummariesByCategoryId(categoryId, 0);
		} else {
			productSummaries = productRepository.selectProductSummariesByCategoryId(categoryId, start);
		}

		return productSummaries;
	}

	@Override
	public int countAllProductSummaries() {
		return productRepository.countAllProductSummaries();
	}

	@Override
	public int countProductSummariesByCategoryId(int categoryId) {
		return productRepository.countProductSummariesByCategoryId(categoryId);
	}

	@Override
	public ProductDescription getProductDescriptionByDisplayInfoId(int displayInfoId) {
		return productRepository.selectProductDescriptionByDisplayInfoId(displayInfoId);
	}

	@Override
	public List<String> getProductImageUrlsByDisplayInfoId(int displayInfoId) {
		List<ProductImage> productImages = productRepository.selectProductImagesByDisplayInfoId(displayInfoId,
			PRODUCT_IMAGES_LIMIT);
		List<String> productImageUrls = new ArrayList<>();

		productImages.stream().forEach(productImage -> productImageUrls.add(productImage.getProductImageUrl()));

		return productImageUrls;
	}
}