package com.react05.fcinema_spring.mapper;

import com.react05.fcinema_spring.entity.CinemaRoom;
import com.react05.fcinema_spring.model.request.roomAndSeat.CinemaRoomRequest;
import com.react05.fcinema_spring.model.request.roomAndSeat.CinemaRoomUpdateRequest;
import com.react05.fcinema_spring.model.response.roomAndSeat.CinemaRoomResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {SeatMapper.class})
public interface CinemaRoomMapper {
    @Mapping(target = "status", ignore = true)
    CinemaRoom toCinemaRoom(CinemaRoomRequest request);

    @Mapping(target = "seats", source = "seats")
    CinemaRoomResponse toResponse(CinemaRoom cinemaRoom);

    List<CinemaRoomResponse> toResponseList(List<CinemaRoom> cinemaRooms);

    @Mapping(target = "status", ignore = true)
    void updateCinemaRoom(@MappingTarget CinemaRoom entity, CinemaRoomUpdateRequest request);
}
