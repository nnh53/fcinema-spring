package com.react05.fcinema_spring.mapper;

import com.react05.fcinema_spring.entity.Seat;
import com.react05.fcinema_spring.model.request.roomAndSeat.SeatRequest;
import com.react05.fcinema_spring.model.response.roomAndSeat.SeatResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SeatMapper {
    Seat toSeat(SeatRequest request);
    @Mapping(target = "roomId", source = "cinemaRoom.id")
    SeatResponse toResponse(Seat seat);
    List<SeatResponse> toResponseList(List<Seat> seats);

    void updateSeat(@MappingTarget Seat entity, SeatRequest request);
}
