package com.react05.fcinema_spring.model.response.MovieAndShowtime;

import com.react05.fcinema_spring.model.response.roomAndSeat.CinemaRoomResponse;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ShowtimeResponse {
  private Integer id;
  private MovieResponse movie;
  private LocalDateTime showDateTime;
  private CinemaRoomResponse room;
  private LocalDateTime endDateTime;
  private String status;
}
