package com.react05.fcinema_spring.service;

import com.react05.fcinema_spring.model.request.Promotion.PromotionRequest;
import com.react05.fcinema_spring.model.request.Promotion.PromotionUpdateRequest;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.promotion.PromotionResponse;
import java.util.List;

public interface PromotionService {
  ApiResponse<PromotionResponse> createPromotion(PromotionRequest request);

  ApiResponse<PromotionResponse> getPromotion(Integer id);

  ApiResponse<List<PromotionResponse>> getAllPromotions();

  ApiResponse<PromotionResponse> updatePromotion(Integer id, PromotionUpdateRequest request);

  ApiResponse<Void> deletePromotion(Integer id);
}
