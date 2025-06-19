package com.react05.fcinema_spring.model.response.roomAndSeat;

import java.util.List;
import lombok.Data;

@Data
public class CinemaRoomResponse {
  private Integer id;
  private int roomNumber;
  private String type;
  private double fee;
  private int capacity;
  private String status;
  private int width;
  private int length;
  private List<SeatResponse> seats;
}
