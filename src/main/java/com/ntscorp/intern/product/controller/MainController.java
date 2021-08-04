package com.ntscorp.intern.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntscorp.intern.product.controller.response.DefaultResponse;
import com.ntscorp.intern.product.model.Category;
import com.ntscorp.intern.product.model.ProductSummary;
import com.ntscorp.intern.product.model.PromotionImage;
import com.ntscorp.intern.product.service.CategoryService;
import com.ntscorp.intern.product.service.ProductService;
import com.ntscorp.intern.product.service.PromotionService;
import com.ntscorp.intern.product.type.CustomHttpStatus;

@RestController
@RequestMapping("/api")
public class MainController {
	private static final Boolean VALID = false;
	private static final Boolean INVALID = true;

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
	@Transactional(readOnly = true)
	public ResponseEntity<DefaultResponse> promotions() {
		List<PromotionImage> promotionImages = promotionService.findAllPromotionImages();

		DefaultResponse response = new DefaultResponse();
		response.setData(promotionImages);
		response.setStatus(CustomHttpStatus.OK.getCode());
		response.setMessage(CustomHttpStatus.OK.getMessage());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(path = "/categories")
	@Transactional(readOnly = true)
	public ResponseEntity<DefaultResponse> categories() {
		List<Category> categories = categoryService.findAllCategories();

		DefaultResponse response = new DefaultResponse();
		response.setData(categories);
		response.setStatus(CustomHttpStatus.OK.getCode());
		response.setMessage(CustomHttpStatus.OK.getMessage());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(path = "/products")
	@Transactional(readOnly = true)
	public ResponseEntity<DefaultResponse> products(
		@RequestParam(required = false)
		Integer categoryId,
		@RequestParam(required = false)
		Integer start) {
		DefaultResponse response = new DefaultResponse();

		if (isNotValidateproducts(categoryId, start)) {
			response.setStatus(CustomHttpStatus.IS_NOT_VALIDATED.getCode());
			response.setMessage(CustomHttpStatus.IS_NOT_VALIDATED.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		List<ProductSummary> productSummaries;
		int totalCount;

		if (categoryId == null) {
			productSummaries = productService.findAllProductSummaries(start);
			totalCount = productService.countAllProductSummaries();
		} else {
			productSummaries = productService.findProductSummariesByCategoryId(categoryId, start);
			totalCount = productService.countProductSummariesByCategoryId(categoryId);
		}

		Map<String, Object> products = new HashMap<>();
		products.put("totalCount", totalCount);
		products.put("products", productSummaries);

		response.setData(products);
		response.setStatus(CustomHttpStatus.OK.getCode());
		response.setMessage(CustomHttpStatus.OK.getMessage());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public Boolean isNotValidateproducts(Integer categoryId, Integer start) {
		if (categoryId == null && start == null) {
			return VALID;
		}

		if (categoryId == null) {
			if (start < 0) {
				return INVALID;
			}
			return VALID;
		}

		if (start == null) {
			if (categoryId < 1) {
				return INVALID;
			}
			return VALID;
		}

		if (categoryId < 1 || start < 0) {
			return INVALID;
		}
		return VALID;
	}
}