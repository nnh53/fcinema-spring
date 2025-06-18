package com.react05.fcinema_spring.mapper;

import com.react05.fcinema_spring.entity.CinemaRoom;
import com.react05.fcinema_spring.model.request.CinemaRoomRequest;
import com.react05.fcinema_spring.model.request.CinemaRoomUpdateRequest;
import com.react05.fcinema_spring.model.response.CinemaRoomResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {SeatMapper.class})
public interface CinemaRoomMapper {
    CinemaRoom toCinemaRoom(CinemaRoomRequest request);

    @Mapping(target = "seats", source = "seats")
    CinemaRoomResponse toResponse(CinemaRoom cinemaRoom);

    List<CinemaRoomResponse> toResponseList(List<CinemaRoom> cinemaRooms);

    void updateCinemaRoom(@MappingTarget CinemaRoom entity, CinemaRoomUpdateRequest request);
}
