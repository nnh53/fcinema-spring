package com.react05.fcinema_spring.mapper;

import com.react05.fcinema_spring.entity.Promotion;
import com.react05.fcinema_spring.model.request.Promotion.PromotionRequest;
import com.react05.fcinema_spring.model.request.Promotion.PromotionUpdateRequest;
import com.react05.fcinema_spring.model.response.promotion.PromotionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PromotionMapper {
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "type", ignore = true)
    Promotion toPromotion(PromotionRequest request);

    PromotionResponse toResponse(Promotion promotion);

    List<PromotionResponse> toResponseList(List<Promotion> promotions);

    void updatePromotion(@MappingTarget Promotion promotion, PromotionUpdateRequest request);
}
