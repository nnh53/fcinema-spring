package com.react05.fcinema_spring.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.react05.fcinema_spring.model.response.Authentication.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "CustomAPIResponse")
public class ApiResponse<T> {
  @Builder.Default
  @Schema(example = "200")
  int code = 200;

  @Schema(example = "Here is your message")
  String message;

  @Schema(
      description =
          "Result data containing the API response payload. This can be any of the response types defined in the application.",
      anyOf = {
        UserResponse.class,
        com.react05.fcinema_spring.model.response.roomAndSeat.CinemaRoomResponse.class,
        com.react05.fcinema_spring.model.response.roomAndSeat.SeatResponse.class,
        com.react05.fcinema_spring.model.response.PageResponse.class,
        com.react05.fcinema_spring.model.response.promotion.PromotionResponse.class,
        com.react05.fcinema_spring.model.response.MovieAndShowtime.ShowtimeResponse.class,
        com.react05.fcinema_spring.model.response.MovieAndShowtime.MovieResponse.class,
        com.react05.fcinema_spring.model.response.Authentication.IntrospectResponse.class,
        com.react05.fcinema_spring.model.response.Authentication.AuthenticationResponse.class,
        java.util.List.class
      })
  T result;
}
