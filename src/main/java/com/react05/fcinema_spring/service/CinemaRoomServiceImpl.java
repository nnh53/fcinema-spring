package com.react05.fcinema_spring.service;

import com.react05.fcinema_spring.entity.CinemaRoom;
import com.react05.fcinema_spring.entity.Seat;
import com.react05.fcinema_spring.exception.AppException;
import com.react05.fcinema_spring.exception.ErrorCode;
import com.react05.fcinema_spring.mapper.CinemaRoomMapper;
import com.react05.fcinema_spring.mapper.SeatMapper;
import com.react05.fcinema_spring.model.request.roomAndSeat.CinemaRoomRequest;
import com.react05.fcinema_spring.model.request.roomAndSeat.CinemaRoomUpdateRequest;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.roomAndSeat.CinemaRoomResponse;
import com.react05.fcinema_spring.repository.CinemaRoomRepository;
import com.react05.fcinema_spring.repository.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaRoomServiceImpl implements CinemaRoomService {
    private final CinemaRoomRepository cinemaRoomRepository;
    private final SeatRepository seatRepository;
    private final CinemaRoomMapper cinemaRoomMapper;
    private final SeatMapper seatMapper;

    private List<Seat> generateDefaultSeats(CinemaRoom cinemaRoom, int width, int length) {
        List<Seat> seats = new java.util.ArrayList<>();
        for (int row = 1; row <= length; row++) {
            char colChar = 'A';
            for (int col = 0; col < width; col++) {
                Seat seat = Seat.builder()
                    .cinemaRoom(cinemaRoom)
                    .row(String.valueOf(row))
                    .column(String.valueOf(colChar))
                    .name(String.valueOf(colChar)+ row)
                    .type(Seat.SeatType.REGULAR)
                    .status(Seat.Status.AVAILABLE)
                    .build();
                seats.add(seat);
                colChar++;
                seatRepository.save(seat);
            }
        }
        return seats;
    }

    @Override
    @Transactional
    public ApiResponse<CinemaRoomResponse> createCinemaRoom(CinemaRoomRequest request) {
        CinemaRoom cinemaRoom = cinemaRoomMapper.toCinemaRoom(request);
        // Assign roomId if not set (manual assignment required)
        cinemaRoom.setStatus(CinemaRoom.Status.MAINTENANCE);
        int width = request.getWidth();
        int length = request.getLength();
        List<Seat> seats = generateDefaultSeats(cinemaRoom, width, length);
        cinemaRoom.setSeats(seats);
        CinemaRoom saved = cinemaRoomRepository.save(cinemaRoom);
        return ApiResponse.<CinemaRoomResponse>builder()
                .code(200)
                .message("Cinema room created successfully")
                .result(cinemaRoomMapper.toResponse(saved))
                .build();
    }

    @Override
    public ApiResponse<CinemaRoomResponse> getCinemaRoom(Integer roomId) {
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(roomId)
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));
        return ApiResponse.<CinemaRoomResponse>builder()
                .code(200)
                .message("Cinema room fetched successfully")
                .result(cinemaRoomMapper.toResponse(cinemaRoom))
                .build();
    }

    @Override
    public ApiResponse<List<CinemaRoomResponse>> getAllCinemaRooms() {
        List<CinemaRoom> rooms = cinemaRoomRepository.findAll();
        return ApiResponse.<List<CinemaRoomResponse>>builder()
                .code(200)
                .message("All cinema rooms fetched successfully")
                .result(cinemaRoomMapper.toResponseList(rooms))
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<CinemaRoomResponse> updateCinemaRoom(Integer roomId, CinemaRoomUpdateRequest request) {
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(roomId)
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));

        if((request.getCapacity() != null && cinemaRoom.getCapacity() != request.getCapacity()) ||
                    (request.getLength() != null && cinemaRoom.getLength() != request.getLength())||
                    (request.getWidth() != null && cinemaRoom.getWidth() != request.getWidth())) {

                    seatRepository.deleteAll(cinemaRoom.getSeats());
                    cinemaRoomMapper.updateCinemaRoom(cinemaRoom, request);
                    cinemaRoom.setStatus(CinemaRoom.Status.MAINTENANCE);
                    cinemaRoom.getSeats().clear();
                    List<Seat> newSeats = generateDefaultSeats(cinemaRoom, request.getWidth(), request.getLength());
                    cinemaRoom.getSeats().addAll(newSeats);
        }else {
            cinemaRoomMapper.updateCinemaRoom(cinemaRoom, request);
        }

        if (request.getStatus() != null && request.getStatus().equals("ACTIVE")) {
            // Count non-PATH seats
            long nonPathSeats = cinemaRoom.getSeats().stream()
                    .filter(seat -> seat.getType() != Seat.SeatType.PATH)
                    .count();

            if (nonPathSeats != cinemaRoom.getCapacity()) {
                throw new AppException(ErrorCode.INVALID_SEAT_CAPACITY);
            }
        }

        CinemaRoom saved = cinemaRoomRepository.save(cinemaRoom);
        return ApiResponse.<CinemaRoomResponse>builder()
                .code(200)
                .message("Cinema room updated successfully")
                .result(cinemaRoomMapper.toResponse(saved))
                .build();
    }


    @Override
    @Transactional
    public ApiResponse<Void> deleteCinemaRoom(Integer roomId) {
        if (!cinemaRoomRepository.existsById(roomId)) {
            throw new AppException(ErrorCode.ROOM_NOT_FOUND);
        }
        cinemaRoomRepository.deleteById(roomId);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Cinema room deleted successfully")
                .build();
    }
}
