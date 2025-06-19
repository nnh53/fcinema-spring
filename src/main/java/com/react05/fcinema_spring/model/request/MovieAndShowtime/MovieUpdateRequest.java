package com.react05.fcinema_spring.model.request.MovieAndShowtime;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MovieUpdateRequest {
    private String name;
    private Integer ageRestrict;
    private String fromDate;
    private String toDate;
    private String actor;
    private String studio;
    private String director;
    @Min(value = 30, message = "MIN_DURATION")
    @Nullable
    private Integer duration;
    private String version;
    private String trailer;
    private String type;
    private String description;
    private String status;
}
