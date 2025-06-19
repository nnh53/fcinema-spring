package com.react05.fcinema_spring.service;

import com.react05.fcinema_spring.model.request.MovieAndShowtime.MovieRequest;
import com.react05.fcinema_spring.model.response.MovieAndShowtime.MovieResponse;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.PageResponse;
import java.util.List;

public interface MovieService {
    ApiResponse<MovieResponse> createMovie(MovieRequest request);
    ApiResponse<MovieResponse> getMovie(Integer id);
    ApiResponse<List<MovieResponse>> getAllMovies();
    ApiResponse<PageResponse<MovieResponse>> getAllMovies(
        int pageNo,
        int pageSize,
        String search,
        String fromDate,
        String toDate,
        String type
    );
    ApiResponse<MovieResponse> updateMovie(Integer id, MovieRequest request);
    ApiResponse<Void> deleteMovie(Integer id);
}
