package com.react05.fcinema_spring.model.request.MovieAndShowtime;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieUpdateRequest {
    private String name;
    @Min(value = 13, message = "UNDER_AGE_RESTRICT")
    @Max(value = 18, message = "UNDER_AGE_RESTRICT")
    @Nullable
    private Integer ageRestrict;
    private LocalDate fromDate;
    private LocalDate toDate;
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
}
