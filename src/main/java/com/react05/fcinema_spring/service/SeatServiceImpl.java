package com.react05.fcinema_spring.service;

import com.react05.fcinema_spring.entity.Seat;
import com.react05.fcinema_spring.exception.AppException;
import com.react05.fcinema_spring.exception.ErrorCode;
import com.react05.fcinema_spring.mapper.SeatMapper;
import com.react05.fcinema_spring.model.request.roomAndSeat.SeatRequest;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.roomAndSeat.SeatResponse;
import com.react05.fcinema_spring.repository.CinemaRoomRepository;
import com.react05.fcinema_spring.repository.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final CinemaRoomRepository cinemaRoomRepository;
    private final SeatMapper seatMapper;

    @Override
    public ApiResponse<SeatResponse> getSeatById(Integer seatId) {
        Seat seat =
                seatRepository
                        .findById(seatId)
                        .orElseThrow(() -> new AppException(ErrorCode.SEAT_NOT_FOUND));
        return ApiResponse.<SeatResponse>builder()
                .code(200)
                .message("Seat fetched successfully")
                .result(seatMapper.toResponse(seat))
                .build();
    }

    @Override
    public ApiResponse<List<SeatResponse>> getAllSeats() {
        List<Seat> seats = seatRepository.findAll();
        return ApiResponse.<List<SeatResponse>>builder()
                .code(200)
                .message("All seats fetched successfully")
                .result(seatMapper.toResponseList(seats))
                .build();
    }

    @Override
    public ApiResponse<List<SeatResponse>> getSeatsByCinemaRoomId(Integer roomId) {
        if (!cinemaRoomRepository.existsById(roomId)) {
            throw new AppException(ErrorCode.ROOM_NOT_FOUND);
        }
        List<Seat> seats = seatRepository.findByCinemaRoom_Id(roomId);
        return ApiResponse.<List<SeatResponse>>builder()
                .code(200)
                .message("Seats for room fetched successfully")
                .result(seatMapper.toResponseList(seats))
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<SeatResponse> updateSeat(Integer seatId, SeatRequest request) {
        Seat seat =
                seatRepository
                        .findById(seatId)
                        .orElseThrow(() -> new AppException(ErrorCode.SEAT_NOT_FOUND));
        if (request.getStatus() != null) {
            seat.setStatus(getSeatStatus(request.getStatus()));
        }
        if (request.getType() != null) {
            seat.setType(getSeatType(request.getType()));
        }
        Seat saved = seatRepository.save(seat);
        return ApiResponse.<SeatResponse>builder()
                .code(200)
                .message("Seat updated successfully")
                .result(seatMapper.toResponse(saved))
                .build();
    }


    private Seat.SeatType getSeatType(String type) {
        try {
            return Seat.SeatType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.INVALID_SEAT_TYPE);
        }
    }

    private Seat.Status getSeatStatus(String status) {
        try {
            return Seat.Status.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.INVALID_SEAT_STATUS);
        }
    }
}
