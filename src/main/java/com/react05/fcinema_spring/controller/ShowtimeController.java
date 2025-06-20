package com.react05.fcinema_spring.controller;

import com.react05.fcinema_spring.model.request.MovieAndShowtime.ShowtimeRequest;
import com.react05.fcinema_spring.model.request.MovieAndShowtime.ShowtimeUpdateRequest;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.MovieAndShowtime.ShowtimeResponse;
import com.react05.fcinema_spring.model.response.PageResponse;
import com.react05.fcinema_spring.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {
    private final ShowtimeService showtimeService;

    @PostMapping
    public ResponseEntity<ApiResponse<ShowtimeResponse>> createShowtime(
            @RequestBody ShowtimeRequest request) {
        return ResponseEntity.ok(showtimeService.createShowtime(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ShowtimeResponse>> getShowtime(@PathVariable Integer id) {
        return ResponseEntity.ok(showtimeService.getShowtime(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ShowtimeResponse>>> getAllShowtimes() {
        return ResponseEntity.ok(showtimeService.getAllShowtimes());
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<ShowtimeResponse>>> searchShowtimes(
            @RequestParam(required = false) Integer movieId,
            @RequestParam(required = false) Integer roomId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endDate,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(
                showtimeService.getShowtimesWithFilters(
                        movieId, roomId, startDate, endDate, status, pageNo, pageSize));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<ApiResponse<List<ShowtimeResponse>>> getShowtimesByMovie(
            @PathVariable Integer movieId) {
        return ResponseEntity.ok(showtimeService.getShowtimesByMovie(movieId));
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<ApiResponse<List<ShowtimeResponse>>> getShowtimesByCinemaRoom(
            @PathVariable Integer roomId) {
        return ResponseEntity.ok(showtimeService.getShowtimesByCinemaRoom(roomId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ShowtimeResponse>> updateShowtime(
            @PathVariable Integer id, @RequestBody ShowtimeUpdateRequest request) {
        return ResponseEntity.ok(showtimeService.updateShowtime(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteShowtime(@PathVariable Integer id) {
        return ResponseEntity.ok(showtimeService.deleteShowtime(id));
    }
}
