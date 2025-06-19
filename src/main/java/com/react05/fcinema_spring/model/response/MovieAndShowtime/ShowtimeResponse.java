package com.react05.fcinema_spring.model.response.MovieAndShowtime;

import com.react05.fcinema_spring.model.response.roomAndSeat.CinemaRoomResponse;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ShowtimeResponse {
    private Integer id;
    private MovieResponse movie;
    private LocalDateTime showDateTime;
    private CinemaRoomResponse room;
    private LocalDateTime endDateTime;
    private String status;
}
