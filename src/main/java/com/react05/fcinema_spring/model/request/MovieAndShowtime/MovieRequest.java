package com.react05.fcinema_spring.model.request.MovieAndShowtime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieRequest {
    @NotEmpty(message = "NOT_EMPTY")
    private String name;

    @NotNull(message = "NOT_EMPTY")
    @Min(value = 13, message = "UNDER_AGE_RESTRICT")
    @Max(value = 18, message = "UNDER_AGE_RESTRICT")
    private Integer ageRestrict;

    @NotNull(message = "NOT_EMPTY")
    private LocalDate fromDate;

    @NotNull(message = "NOT_EMPTY")
    private LocalDate toDate;

    @NotEmpty(message = "NOT_EMPTY")
    private String actor;

    @NotEmpty(message = "NOT_EMPTY")
    private String studio;

    @NotEmpty(message = "NOT_EMPTY")
    private String director;

    @Min(value = 30, message = "MIN_DURATION")
    private Integer duration;

    @NotEmpty(message = "NOT_EMPTY")
    private String version;

    @NotEmpty(message = "NOT_EMPTY")
    private String trailer;

    @NotEmpty(message = "NOT_EMPTY")
    private String type;

    @NotEmpty(message = "NOT_EMPTY")
    private String description;

    @NotEmpty(message = "NOT_EMPTY")
    private String status;

    @NotEmpty(message = "NOT_EMPTY")
    private String poster;
}
