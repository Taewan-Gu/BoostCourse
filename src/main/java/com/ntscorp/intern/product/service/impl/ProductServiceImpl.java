package com.ntscorp.intern.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntscorp.intern.product.model.ProductSummary;
import com.ntscorp.intern.product.repository.ProductRepository;
import com.ntscorp.intern.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<ProductSummary> findAllProductSummaries(Integer start) {
		List<ProductSummary> productSummaries;

		if (start == null) {
			productSummaries = productRepository.findAllProductSummaries(0);
		} else {
			productSummaries = productRepository.findAllProductSummaries(start);
		}

		return productSummaries;
	}

	@Override
	public List<ProductSummary> findProductSummariesByCategoryId(Integer categoryId, Integer start) {
		List<ProductSummary> productSummaries;

		if (start == null) {
			productSummaries = productRepository.findProductSummariesByCategoryId(categoryId, 0);
		} else {
			productSummaries = productRepository.findProductSummariesByCategoryId(categoryId, start);
		}

		return productSummaries;
	}

	@Override
	public int countAllProductSummaries() {
		return productRepository.countAllProductSummaries();
	}

	@Override
	public int countProductSummariesByCategoryId(Integer categoryId) {
		return productRepository.countProductSummariesByCategoryId(categoryId);
	}
}