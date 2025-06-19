package com.react05.fcinema_spring.controller;

import com.react05.fcinema_spring.model.request.roomAndSeat.SeatRequest;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.roomAndSeat.SeatResponse;
import com.react05.fcinema_spring.service.SeatService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

  private final SeatService seatService;

  @GetMapping
  public ResponseEntity<ApiResponse<List<SeatResponse>>> getAllSeats() {
    return ResponseEntity.ok(seatService.getAllSeats());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<SeatResponse>> getSeatById(@PathVariable Integer id) {
    return ResponseEntity.ok(seatService.getSeatById(id));
  }

  @GetMapping("/room/{roomId}")
  public ResponseEntity<ApiResponse<List<SeatResponse>>> getSeatsByCinemaRoomId(
      @PathVariable Integer roomId) {
    return ResponseEntity.ok(seatService.getSeatsByCinemaRoomId(roomId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<SeatResponse>> updateSeat(
      @PathVariable Integer id, @RequestBody SeatRequest request) {
    return ResponseEntity.ok(seatService.updateSeat(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> deleteSeat(@PathVariable Integer id) {
    return ResponseEntity.ok(seatService.deleteSeat(id));
  }
}
