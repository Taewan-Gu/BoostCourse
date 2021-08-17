package com.ntscorp.intern.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntscorp.intern.product.controller.response.CategoriesResponse;
import com.ntscorp.intern.product.controller.response.ProductsResponse;
import com.ntscorp.intern.product.controller.response.PromotionsResponse;
import com.ntscorp.intern.product.model.Category;
import com.ntscorp.intern.product.model.ProductSummary;
import com.ntscorp.intern.product.model.Promotion;
import com.ntscorp.intern.product.service.CategoryService;
import com.ntscorp.intern.product.service.ProductService;
import com.ntscorp.intern.product.service.PromotionService;

@RestController
@RequestMapping("/api")
public class MainController {
	private static final boolean VALID = false;
	private static final boolean INVALID = true;
	private static final int MIN_CATEGORY_ID = 1;
	private static final int MIN_START = 0;

	private final PromotionService promotionService;
	private final CategoryService categoryService;
	private final ProductService productService;

	@Autowired
	public MainController(PromotionService promotionService, CategoryService categoryService,
		ProductService productService) {
		this.promotionService = promotionService;
		this.categoryService = categoryService;
		this.productService = productService;
	}

	@GetMapping(path = "/promotions")
	public ResponseEntity<PromotionsResponse> getPromotions() {
		List<Promotion> promotions = promotionService.getAllPromotions();

		PromotionsResponse promotionsResponse = new PromotionsResponse(promotions);

		return ResponseEntity.ok(promotionsResponse);
	}

	@GetMapping(path = "/categories")
	public ResponseEntity<CategoriesResponse> getCategories() {
		List<Category> categories = categoryService.getAllCategories();

		CategoriesResponse categoriesResponse = new CategoriesResponse(categories);

		return ResponseEntity.ok(categoriesResponse);
	}

	@GetMapping(path = "/products", params = {"categoryId"})
	public ResponseEntity<ProductsResponse> getProductsByCategory(
		@RequestParam
		int categoryId,
		@RequestParam(defaultValue = "0")
		int start) {

		if (isNotValidateProducts(categoryId, start)) {
			throw new IllegalArgumentException("arguments = [categoryId: " + categoryId + ", start: " + start + "]");
		}

		List<ProductSummary> productSummaries = productService.getProductSummariesByCategoryId(categoryId, start);
		int totalCount = productService.countProductSummariesByCategoryId(categoryId);

		ProductsResponse productsResponse = new ProductsResponse(totalCount, productSummaries);

		return ResponseEntity.ok(productsResponse);
	}

	@GetMapping(path = "/products")
	public ResponseEntity<ProductsResponse> getProducts(
		@RequestParam(defaultValue = "0")
		int start) {

		if (isNotValidateProducts(start)) {
			throw new IllegalArgumentException("arguments = [start: " + start + "]");
		}

		List<ProductSummary> productSummaries = productService.getAllProductSummaries(start);
		int totalCount = productService.countAllProductSummaries();

		ProductsResponse productsResponse = new ProductsResponse(totalCount, productSummaries);

		return ResponseEntity.ok(productsResponse);
	}

	private boolean isNotValidateProducts(int start) {
		if (start < MIN_START) {
			return INVALID;
		}
		return VALID;
	}

	private boolean isNotValidateProducts(int categoryId, int start) {
		if (categoryId < MIN_CATEGORY_ID || start < MIN_START) {
			return INVALID;
		}
		return VALID;
	}
}