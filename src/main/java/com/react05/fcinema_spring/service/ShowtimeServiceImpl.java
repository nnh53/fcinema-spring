package com.react05.fcinema_spring.service;

import com.react05.fcinema_spring.entity.CinemaRoom;
import com.react05.fcinema_spring.entity.Movie;
import com.react05.fcinema_spring.entity.Showtime;
import com.react05.fcinema_spring.exception.AppException;
import com.react05.fcinema_spring.exception.ErrorCode;
import com.react05.fcinema_spring.mapper.ShowtimeMapper;
import com.react05.fcinema_spring.model.request.MovieAndShowtime.ShowtimeRequest;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.PageResponse;
import com.react05.fcinema_spring.model.response.MovieAndShowtime.ShowtimeResponse;
import com.react05.fcinema_spring.repository.CinemaRoomRepository;
import com.react05.fcinema_spring.repository.MovieRepository;
import com.react05.fcinema_spring.repository.ShowtimeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {
    private final ShowtimeRepository showtimeRepository;
    private final MovieRepository movieRepository;
    private final CinemaRoomRepository cinemaRoomRepository;
    private final ShowtimeMapper showtimeMapper;

    @Override
    @Transactional
    public ApiResponse<ShowtimeResponse> createShowtime(ShowtimeRequest request) {
        // Validate movie exists
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));

        // Validate cinema room exists
        CinemaRoom room = cinemaRoomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));

        // Validate showtime dates
        validateShowtimeDates(request, movie);

        // Check for conflicting showtimes
        validateNoConflicts(room, request.getShowDateTime(), request.getEndDateTime());

        Showtime showtime = showtimeMapper.toShowtime(request);
        showtime.setMovie(movie);
        showtime.setRoom(room);

        // Set status
        if (request.getStatus() != null) {
            try {
                showtime.setStatus(Showtime.Status.valueOf(request.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new AppException(ErrorCode.INVALID_STATUS);
            }
        } else {
            // Default status
            showtime.setStatus(Showtime.Status.SCHEDULE);
        }

        Showtime savedShowtime = showtimeRepository.save(showtime);
        return ApiResponse.<ShowtimeResponse>builder()
                .result(showtimeMapper.toResponse(savedShowtime))
                .build();
    }

    @Override
    public ApiResponse<ShowtimeResponse> getShowtime(Integer id) {
        Showtime showtime = showtimeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SHOWTIME_NOT_FOUND));
        return ApiResponse.<ShowtimeResponse>builder()
                .result(showtimeMapper.toResponse(showtime))
                .build();
    }

    @Override
    public ApiResponse<List<ShowtimeResponse>> getAllShowtimes() {
        List<Showtime> showtimes = showtimeRepository.findAll();
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .result(showtimeMapper.toResponseList(showtimes))
                .build();
    }

    @Override
    public ApiResponse<PageResponse<ShowtimeResponse>> getShowtimesWithFilters(
            Integer movieId, Integer roomId, LocalDateTime startDate,
            LocalDateTime endDate, String status, int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Showtime.Status statusEnum = null;
        if (status != null && !status.isEmpty()) {
            try {
                statusEnum = Showtime.Status.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new AppException(ErrorCode.INVALID_STATUS);
            }
        }

        Page<Showtime> showtimePage = showtimeRepository.findWithFilters(
                movieId, roomId, startDate, endDate, statusEnum, pageable);

        List<ShowtimeResponse> content = showtimeMapper.toResponseList(showtimePage.getContent());

        PageResponse<ShowtimeResponse> pageResponse = PageResponse.<ShowtimeResponse>builder()
                .content(content)
                .pageNo(showtimePage.getNumber())
                .pageSize(showtimePage.getSize())
                .totalElements(showtimePage.getTotalElements())
                .totalPages(showtimePage.getTotalPages())
                .last(showtimePage.isLast())
                .build();

        return ApiResponse.<PageResponse<ShowtimeResponse>>builder()
                .result(pageResponse)
                .build();
    }

    @Override
    public ApiResponse<List<ShowtimeResponse>> getShowtimesByMovie(Integer movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));
        List<Showtime> showtimes = showtimeRepository.findByMovie(movie);
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .result(showtimeMapper.toResponseList(showtimes))
                .build();
    }

    @Override
    public ApiResponse<List<ShowtimeResponse>> getShowtimesByCinemaRoom(Integer roomId) {
        CinemaRoom room = cinemaRoomRepository.findById(roomId)
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));
        List<Showtime> showtimes = showtimeRepository.findByRoom(room);
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .result(showtimeMapper.toResponseList(showtimes))
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<ShowtimeResponse> updateShowtime(Integer id, ShowtimeRequest request) {
        // Find existing showtime
        Showtime showtime = showtimeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SHOWTIME_NOT_FOUND));

        // Handle movie update if provided
        if (request.getMovieId() != null) {
            Movie movie = movieRepository.findById(request.getMovieId())
                    .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));
            showtime.setMovie(movie);
        }

        // Handle room update if provided
        if (request.getRoomId() != null) {
            CinemaRoom room = cinemaRoomRepository.findById(request.getRoomId())
                    .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));
            showtime.setRoom(room);
        }

        // Update basic fields
        showtimeMapper.updateShowtime(showtime, request);

        // If dates are modified, validate them
        if (request.getShowDateTime() != null || request.getEndDateTime() != null) {
            LocalDateTime showStartTime = request.getShowDateTime() != null ?
                    request.getShowDateTime() : showtime.getShowDateTime();
            LocalDateTime showEndTime = request.getEndDateTime() != null ?
                    request.getEndDateTime() : showtime.getEndDateTime();

            validateShowtimeDates(showStartTime, showEndTime, showtime.getMovie());

            // Check conflicts (exclude the current showtime)
            List<Showtime> conflicts = showtimeRepository.findConflictingShowtimes(
                    showtime.getRoom(), showStartTime, showEndTime);
            conflicts.removeIf(s -> s.getId().equals(id));

            if (!conflicts.isEmpty()) {
                throw new AppException(ErrorCode.SHOWTIME_CONFLICT);
            }
        }

        // Handle status update if provided
        if (request.getStatus() != null) {
            try {
                showtime.setStatus(Showtime.Status.valueOf(request.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new AppException(ErrorCode.INVALID_STATUS);
            }
        }

        Showtime updatedShowtime = showtimeRepository.save(showtime);
        return ApiResponse.<ShowtimeResponse>builder()
                .result(showtimeMapper.toResponse(updatedShowtime))
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteShowtime(Integer id) {
        if (!showtimeRepository.existsById(id)) {
            throw new AppException(ErrorCode.SHOWTIME_NOT_FOUND);
        }

        showtimeRepository.deleteById(id);
        return ApiResponse.<Void>builder()
                .message("Delete Successfully")
                .build();
    }

    // Helper methods for validation

    private void validateShowtimeDates(ShowtimeRequest request, Movie movie) {
        validateShowtimeDates(request.getShowDateTime(), request.getEndDateTime(), movie);
    }

    private void validateShowtimeDates(LocalDateTime showStartTime, LocalDateTime showEndTime, Movie movie) {
        // Validate showtime order
        if (showStartTime == null || showEndTime == null) {
            throw new AppException(ErrorCode.INVALID_SHOWTIME);
        }

        if (showStartTime.isAfter(showEndTime) || showStartTime.isEqual(showEndTime)) {
            throw new AppException(ErrorCode.INVALID_SHOWTIME);
        }

        // Check if movie is available in this date range
        LocalDate showDate = showStartTime.toLocalDate();

        // Check if fromDate and toDate are available in movie
        if (movie.getFromDate() == null || movie.getToDate() == null) {
            throw new AppException(ErrorCode.MOVIE_NOT_AVAILABLE);
        }

        if (showDate.isBefore(movie.getFromDate()) || showDate.isAfter(movie.getToDate())) {
            throw new AppException(ErrorCode.MOVIE_NOT_AVAILABLE);
        }
    }

    private void validateNoConflicts(CinemaRoom room, LocalDateTime startTime, LocalDateTime endTime) {
        List<Showtime> conflicts = showtimeRepository.findConflictingShowtimes(room, startTime, endTime);
        if (!conflicts.isEmpty()) {
            throw new AppException(ErrorCode.SHOWTIME_CONFLICT);
        }
    }
}
