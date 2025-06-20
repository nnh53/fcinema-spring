package com.react05.fcinema_spring.mapper;

import com.react05.fcinema_spring.entity.Movie;
import com.react05.fcinema_spring.model.request.MovieAndShowtime.MovieRequest;
import com.react05.fcinema_spring.model.request.MovieAndShowtime.MovieUpdateRequest;
import com.react05.fcinema_spring.model.response.MovieAndShowtime.MovieResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MovieMapper {
    @Mapping(target = "status", ignore = true)
    Movie toMovie(MovieRequest request);

    //    @Mapping(target = "showtimes", ignore = true)
    MovieResponse toResponse(Movie movie);

    List<MovieResponse> toResponseList(List<Movie> movies);

    @Mapping(target = "status", ignore = true)
    void updateMovie(@MappingTarget Movie entity, MovieUpdateRequest request);
}
