package com.react05.fcinema_spring.model.request.Promotion;

import com.react05.fcinema_spring.entity.Promotion;
import com.react05.fcinema_spring.exception.ErrorCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionRequest {
    private String image;
    @NotBlank(message = "NOT_NULL")
    private String title;
    @NotBlank(message = "NOT_NULL")
    private String type;

    @NotNull(message = "NOT_NULL")
    private Double minPurchase;

    @NotNull(message = "NOT_NULL")
    private Double discountValue;

    @NotNull(message = "NOT_NULL")
    private LocalDateTime startTime;
    @NotNull(message = "NOT_NULL")
    private LocalDateTime endTime;
    @NotBlank(message = "NOT_NULL")
    private String description;
    @NotBlank(message = "NOT_NULL")
    private Promotion.PromotionStatus status;
}
