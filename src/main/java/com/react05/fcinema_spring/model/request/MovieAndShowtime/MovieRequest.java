package com.react05.fcinema_spring.model.request.MovieAndShowtime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MovieRequest {
  @NotEmpty(message = "NOT_EMPTY")
  private String name;

  @NotEmpty(message = "NOT_EMPTY")
  @Size(max = 18, min = 13, message = "UNDER_AGE_RESTRICT")
  private Integer ageRestrict;

  @NotEmpty(message = "NOT_EMPTY")
  private String fromDate;

  @NotEmpty(message = "NOT_EMPTY")
  private String toDate;

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
}
