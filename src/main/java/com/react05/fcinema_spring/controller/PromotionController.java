package com.react05.fcinema_spring.controller;

import com.react05.fcinema_spring.model.request.Promotion.PromotionRequest;
import com.react05.fcinema_spring.model.request.Promotion.PromotionUpdateRequest;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.promotion.PromotionResponse;
import com.react05.fcinema_spring.service.PromotionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PromotionController {
    PromotionService promotionService;

    @PostMapping
    public ResponseEntity<ApiResponse<PromotionResponse>> createPromotion(@RequestBody PromotionRequest request) {
        return ResponseEntity.ok(promotionService.createPromotion(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PromotionResponse>> getPromotion(@PathVariable Integer id) {
        return ResponseEntity.ok(promotionService.getPromotion(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PromotionResponse>>> getAllPromotions() {
        return ResponseEntity.ok(promotionService.getAllPromotions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PromotionResponse>> updatePromotion(@PathVariable Integer id, @RequestBody PromotionUpdateRequest request) {
        return ResponseEntity.ok(promotionService.updatePromotion(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePromotion(@PathVariable Integer id) {
        return ResponseEntity.ok(promotionService.deletePromotion(id));
    }
}
