package com.ntscorp.intern.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntscorp.intern.product.controller.response.ProductDescriptionResponse;
import com.ntscorp.intern.product.model.ProductDescription;
import com.ntscorp.intern.product.service.ProductService;

@RestController
@RequestMapping("/api")
public class DetailController {

	private final ProductService productService;

	@Autowired
	public DetailController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products/{displayInfoId}")
	public ResponseEntity<ProductDescriptionResponse> getDisplay(@PathVariable
	int displayInfoId) {

		ProductDescription productDescription = productService.selectProductDescriptionByDisplayInfoId(displayInfoId);
		List<String> productImageUrls = productService.selectProductImageUrlsByDisplayInfoId(displayInfoId);

		ProductDescriptionResponse productDescriptionResponse = new ProductDescriptionResponse(productDescription,
			productImageUrls);

		return ResponseEntity.ok(productDescriptionResponse);
	}
}