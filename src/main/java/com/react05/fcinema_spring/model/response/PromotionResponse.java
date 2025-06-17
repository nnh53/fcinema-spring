package com.react05.fcinema_spring.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionResponse {
    private Long id;
    private String image;
    private String title;
    private String type;
    private Double minPurchase;
    private Double discountValue;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private String status;
}
