package com.react05.fcinema_spring.model.response.roomAndSeat;

import lombok.Data;

@Data
public class SeatResponse {
  private Integer id;
  private Integer roomId;
  private String row;
  private String column;
  private String name;
  private String type;
  private String status;
}
