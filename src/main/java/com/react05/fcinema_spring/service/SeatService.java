package com.react05.fcinema_spring.service;



import com.react05.fcinema_spring.model.request.SeatRequest;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.SeatResponse;

import java.util.List;

public interface SeatService {
     ApiResponse<List<SeatResponse>> getAllSeats();
     ApiResponse<SeatResponse> getSeatById(Integer id);
     ApiResponse<List<SeatResponse>>  getSeatsByCinemaRoomId(Integer roomId);
     ApiResponse<SeatResponse> updateSeat(Integer seatId, SeatRequest request);
     ApiResponse<Void>  deleteSeat(Integer id);
}
