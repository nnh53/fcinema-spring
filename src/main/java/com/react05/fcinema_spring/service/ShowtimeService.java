package com.react05.fcinema_spring.service;

import com.react05.fcinema_spring.model.request.MovieAndShowtime.ShowtimeRequest;
import com.react05.fcinema_spring.model.request.MovieAndShowtime.ShowtimeUpdateRequest;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.MovieAndShowtime.ShowtimeResponse;
import com.react05.fcinema_spring.model.response.PageResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowtimeService {
    ApiResponse<ShowtimeResponse> createShowtime(ShowtimeRequest request);

    ApiResponse<ShowtimeResponse> getShowtime(Integer id);

    ApiResponse<List<ShowtimeResponse>> getAllShowtimes();

    ApiResponse<PageResponse<ShowtimeResponse>> getShowtimesWithFilters(
            Integer movieId,
            Integer roomId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            String status,
            int pageNo,
            int pageSize);

    ApiResponse<List<ShowtimeResponse>> getShowtimesByMovie(Integer movieId);

    ApiResponse<List<ShowtimeResponse>> getShowtimesByCinemaRoom(Integer roomId);

    ApiResponse<ShowtimeResponse> updateShowtime(Integer id, ShowtimeUpdateRequest request);

    ApiResponse<Void> deleteShowtime(Integer id);
}
