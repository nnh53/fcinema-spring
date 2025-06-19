package com.react05.fcinema_spring.model.request.MovieAndShowtime;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowtimeRequest {
    private Integer movieId;
    private LocalDateTime showDateTime;
    private Integer roomId;
    private LocalDateTime endDateTime;
    private String status;
}
