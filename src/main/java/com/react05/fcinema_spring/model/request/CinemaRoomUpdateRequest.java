package com.react05.fcinema_spring.model.request;

import lombok.Data;

@Data
public class CinemaRoomUpdateRequest {
    private Integer roomNumber;
    private String type;
    private Double fee;
    private Integer capacity;
    private String status;
    private Integer width;
    private Integer length;
}

