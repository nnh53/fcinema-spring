package com.react05.fcinema_spring.model.response.MovieAndShowtime;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowtimeResponse {
    private Integer id;
    private Integer movieId;
    private LocalDateTime showDateTime;
    private Integer cinemaRoomId;
    private LocalDateTime endDateTime;
    private String status;
}
