package com.react05.fcinema_spring.mapper;

import com.react05.fcinema_spring.entity.Showtime;
import com.react05.fcinema_spring.model.request.MovieAndShowtime.ShowtimeRequest;
import com.react05.fcinema_spring.model.request.MovieAndShowtime.ShowtimeUpdateRequest;
import com.react05.fcinema_spring.model.response.MovieAndShowtime.ShowtimeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {MovieMapper.class, CinemaRoomMapper.class})
public interface ShowtimeMapper {

    @Mapping(target = "movie", ignore = true)
    @Mapping(target = "cinemaRoom", ignore = true)
    @Mapping(target = "status", ignore = true)
    Showtime toShowtime(ShowtimeRequest request);

    @Mapping(target = "movieId", source = "movie.id")
    @Mapping(target = "cinemaRoomId", source = "cinemaRoom.id")
    ShowtimeResponse toResponse(Showtime showtime);

    List<ShowtimeResponse> toResponseList(List<Showtime> showtimes);


    @Mapping(target = "status", ignore = true)
    void updateShowtime(@MappingTarget Showtime entity, ShowtimeUpdateRequest request);
}
