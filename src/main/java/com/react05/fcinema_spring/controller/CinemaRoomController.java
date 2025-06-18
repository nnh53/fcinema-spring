package com.react05.fcinema_spring.controller;


import com.react05.fcinema_spring.model.request.CinemaRoomRequest;
import com.react05.fcinema_spring.model.request.CinemaRoomUpdateRequest;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.CinemaRoomResponse;
import com.react05.fcinema_spring.service.CinemaRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema-rooms")
@RequiredArgsConstructor
public class CinemaRoomController {
    private final CinemaRoomService cinemaRoomService;

    @PostMapping
    public ResponseEntity<ApiResponse<CinemaRoomResponse>> create(@RequestBody CinemaRoomRequest request) {
        return ResponseEntity.ok(cinemaRoomService.createCinemaRoom(request));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ApiResponse<CinemaRoomResponse>> get(@PathVariable Integer roomId) {
        return ResponseEntity.ok(cinemaRoomService.getCinemaRoom(roomId));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CinemaRoomResponse>>> getAll() {
        return ResponseEntity.ok(cinemaRoomService.getAllCinemaRooms());
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<ApiResponse<CinemaRoomResponse>> update(@PathVariable Integer roomId, @RequestBody CinemaRoomUpdateRequest request) {
        return ResponseEntity.ok(cinemaRoomService.updateCinemaRoom(roomId, request));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer roomId) {
        return ResponseEntity.ok(cinemaRoomService.deleteCinemaRoom(roomId));
    }
}

