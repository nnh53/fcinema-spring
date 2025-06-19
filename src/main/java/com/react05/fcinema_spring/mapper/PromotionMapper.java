package com.react05.fcinema_spring.mapper;

import com.react05.fcinema_spring.entity.Promotion;
import com.react05.fcinema_spring.model.request.Promotion.PromotionRequest;
import com.react05.fcinema_spring.model.response.promotion.PromotionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PromotionMapper {
    Promotion toPromotion(PromotionRequest request);

    PromotionResponse toResponse(Promotion promotion);

    void updatePromotion(@MappingTarget Promotion promotion, PromotionRequest request);
}
