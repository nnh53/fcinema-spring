package com.react05.fcinema_spring.model.response.MovieAndShowtime;

import lombok.Data;

import java.util.List;

@Data
public class MovieResponse {
    private Integer id;
    private String name;
    private Integer ageRestrict;
    private String fromDate;
    private String toDate;
    private String actor;
    private String studio;
    private String director;
    private Integer duration;
    private String version;
    private String trailer;
    private String type;
    private String description;
    private String status;
    private String poster;
    private List<ShowtimeResponse> showtimes;
}
