package com.react05.fcinema_spring.service;

import com.react05.fcinema_spring.entity.Movie;
import com.react05.fcinema_spring.exception.AppException;
import com.react05.fcinema_spring.exception.ErrorCode;
import com.react05.fcinema_spring.mapper.MovieMapper;
import com.react05.fcinema_spring.mapper.ShowtimeMapper;
import com.react05.fcinema_spring.model.request.MovieAndShowtime.MovieRequest;
import com.react05.fcinema_spring.model.request.MovieAndShowtime.MovieUpdateRequest;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.MovieAndShowtime.MovieResponse;
import com.react05.fcinema_spring.model.response.PageResponse;
import com.react05.fcinema_spring.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final ShowtimeMapper showtimeMapper;

    @Override
    @Transactional
    public ApiResponse<MovieResponse> createMovie(MovieRequest request) {
        Movie movie = movieMapper.toMovie(request);

        movie.setStatus(getMovieStatus(request.getStatus()));

        Movie savedMovie = movieRepository.save(movie);

        var result = movieMapper.toResponse(savedMovie);
        return ApiResponse.<MovieResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

    @Override
    public ApiResponse<MovieResponse> getMovie(Integer id) {
        Movie movie =
                movieRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));
        var result = movieMapper.toResponse(movie);
        var showtimes = showtimeMapper.toResponseList(movie.getShowtimes());
        result.setShowtimes(showtimes);
        return ApiResponse.<MovieResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

    @Override
    public ApiResponse<List<MovieResponse>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return ApiResponse.<List<MovieResponse>>builder()
                .code(200)
                .result(movieMapper.toResponseList(movies))
                .build();
    }

    @Override
    public ApiResponse<PageResponse<MovieResponse>> getAllMovies(
            int pageNo, int pageSize, String search, String fromDate, String toDate, String type) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        // Handle null or empty values
        String searchParam = (search != null && !search.trim().isEmpty()) ? search : null;
        LocalDate fromDateParam =
                (fromDate != null && !fromDate.trim().isEmpty()) ? LocalDate.parse(fromDate) : null;
        LocalDate toDateParam =
                (toDate != null && !toDate.trim().isEmpty()) ? LocalDate.parse(toDate) : null;
        String typeParam = (type != null && !type.trim().isEmpty()) ? type : null;

        Page<Movie> moviePage =
                movieRepository.findMoviesWithFilters(
                        searchParam, fromDateParam, toDateParam, typeParam, pageable);

        List<MovieResponse> content = movieMapper.toResponseList(moviePage.getContent());

        PageResponse<MovieResponse> pageResponse =
                PageResponse.<MovieResponse>builder()
                        .content(content)
                        .pageNo(moviePage.getNumber())
                        .pageSize(moviePage.getSize())
                        .totalElements(moviePage.getTotalElements())
                        .totalPages(moviePage.getTotalPages())
                        .last(moviePage.isLast())
                        .build();

        return ApiResponse.<PageResponse<MovieResponse>>builder()
                .code(200)
                .result(pageResponse)
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<MovieResponse> updateMovie(Integer id, MovieUpdateRequest request) {
        Movie movie =
                movieRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));

        movieMapper.updateMovie(movie, request);
        movie.setStatus(getMovieStatus(request.getStatus()));
        Movie updatedMovie = movieRepository.save(movie);
        var result = movieMapper.toResponse(updatedMovie);
        var showtimes = showtimeMapper.toResponseList(movie.getShowtimes());
        result.setShowtimes(showtimes);
        return ApiResponse.<MovieResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteMovie(Integer id) {
        if (!movieRepository.existsById(id)) {
            throw new AppException(ErrorCode.MOVIE_NOT_FOUND);
        }
        movieRepository.deleteById(id);
        return ApiResponse.<Void>builder().code(200).message("Movie deleted successfully").build();
    }

    private Movie.Status getMovieStatus(String status) {
        try {
            return Movie.Status.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.INVALID_STATUS);
        }
    }
}
