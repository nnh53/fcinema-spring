package com.react05.fcinema_spring.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CinemaRoomRequest {
    @NotNull(message = "NOT_NULL")
    private Integer roomNumber;
    @NotNull(message = "NOT_NULL")
    private String type;
    @NotNull(message = "NOT_NULL")
    private Double fee;
    @NotNull(message = "NOT_NULL")
    private Integer capacity;
    @NotNull(message = "NOT_NULL")
    private Integer width;
    @NotNull(message = "NOT_NULL")
    private Integer length;
}

