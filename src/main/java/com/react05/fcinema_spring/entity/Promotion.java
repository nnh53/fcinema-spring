package com.react05.fcinema_spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String image;
    private String title;
    private PromotionType type;
    private Double minPurchase = 0.0;
    private Double discountValue = 0.0;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private PromotionStatus status;

    public enum PromotionStatus {
        ACTIVE, INACTIVE
    }

    public enum PromotionType {
        PERCENTAGE, AMOUNT
    }
}
