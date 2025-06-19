package com.react05.fcinema_spring.mapper;

import com.react05.fcinema_spring.entity.Movie;
import com.react05.fcinema_spring.model.request.MovieAndShowtime.MovieRequest;
import com.react05.fcinema_spring.model.request.MovieAndShowtime.MovieUpdateRequest;
import com.react05.fcinema_spring.model.response.MovieAndShowtime.MovieResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MovieMapper {
  @Mapping(target = "status", ignore = true)
  Movie toMovie(MovieRequest request);

  MovieResponse toResponse(Movie movie);

  List<MovieResponse> toResponseList(List<Movie> movies);

  void updateMovie(@MappingTarget Movie entity, MovieUpdateRequest request);
}
