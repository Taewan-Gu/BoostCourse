package com.ntscorp.intern.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntscorp.intern.product.model.PromotionImage;
import com.ntscorp.intern.product.repository.PromotionRepository;
import com.ntscorp.intern.product.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {
	private final PromotionRepository promotionRepository;

	@Autowired
	public PromotionServiceImpl(PromotionRepository promotionRepository) {
		this.promotionRepository = promotionRepository;
	}

	@Override
	public List<PromotionImage> findAllPromotionImages() {
		return promotionRepository.findAllPromotionImages();
	}
}