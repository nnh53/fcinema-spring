package com.react05.fcinema_spring.model.request.MovieAndShowtime;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowtimeUpdateRequest {
    private LocalDateTime showDateTime;
    private LocalDateTime endDateTime;
    private String status;
}
