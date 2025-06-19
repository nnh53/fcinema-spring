package com.react05.fcinema_spring.service;

import com.react05.fcinema_spring.entity.Promotion;
import com.react05.fcinema_spring.exception.AppException;
import com.react05.fcinema_spring.exception.ErrorCode;
import com.react05.fcinema_spring.mapper.PromotionMapper;
import com.react05.fcinema_spring.model.request.Promotion.PromotionRequest;
import com.react05.fcinema_spring.model.request.Promotion.PromotionUpdateRequest;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.promotion.PromotionResponse;
import com.react05.fcinema_spring.repository.PromotionRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
  private final PromotionRepository promotionRepository;
  private final PromotionMapper promotionMapper;

  @Override
  @Transactional
  public ApiResponse<PromotionResponse> createPromotion(PromotionRequest request) {
    Promotion promotion = promotionMapper.toPromotion(request);
    PromotionResponse response = promotionMapper.toResponse(promotionRepository.save(promotion));
    return ApiResponse.<PromotionResponse>builder()
        .code(200)
        .message("Promotion created successfully")
        .result(response)
        .build();
  }

  @Override
  public ApiResponse<PromotionResponse> getPromotion(Integer id) {
    Promotion promotion =
        promotionRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.PROMOTION_NOT_FOUND));
    PromotionResponse response = promotionMapper.toResponse(promotion);
    return ApiResponse.<PromotionResponse>builder()
        .code(200)
        .message("Promotion fetched successfully")
        .result(response)
        .build();
  }

  @Override
  public ApiResponse<List<PromotionResponse>> getAllPromotions() {
    List<PromotionResponse> responses =
        promotionRepository.findAll().stream()
            .map(promotionMapper::toResponse)
            .collect(Collectors.toList());
    return ApiResponse.<List<PromotionResponse>>builder()
        .code(200)
        .message("All promotions fetched successfully")
        .result(responses)
        .build();
  }

  @Override
  @Transactional
  public ApiResponse<PromotionResponse> updatePromotion(
      Integer id, PromotionUpdateRequest request) {
    Promotion promotion =
        promotionRepository
            .findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.PROMOTION_NOT_FOUND));
    promotionMapper.updatePromotion(promotion, request);
    PromotionResponse response = promotionMapper.toResponse(promotionRepository.save(promotion));
    return ApiResponse.<PromotionResponse>builder()
        .code(200)
        .message("Promotion updated successfully")
        .result(response)
        .build();
  }

  @Override
  @Transactional
  public ApiResponse<Void> deletePromotion(Integer id) {
    if (!promotionRepository.existsById(id)) {
      throw new AppException(ErrorCode.PROMOTION_NOT_FOUND);
    }
    promotionRepository.deleteById(id);
    return ApiResponse.<Void>builder().code(200).message("Promotion deleted successfully").build();
  }
}
