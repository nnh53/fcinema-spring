package com.react05.fcinema_spring.service;


import com.react05.fcinema_spring.model.request.CinemaRoomRequest;
import com.react05.fcinema_spring.model.request.CinemaRoomUpdateRequest;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.CinemaRoomResponse;

import java.util.List;

public interface CinemaRoomService {
    ApiResponse<CinemaRoomResponse> createCinemaRoom(CinemaRoomRequest request);
    ApiResponse<CinemaRoomResponse> getCinemaRoom(Integer roomId);
    ApiResponse<List<CinemaRoomResponse>> getAllCinemaRooms();
    ApiResponse<CinemaRoomResponse> updateCinemaRoom(Integer roomId, CinemaRoomUpdateRequest request);
    ApiResponse<Void> deleteCinemaRoom(Integer roomId);
}

