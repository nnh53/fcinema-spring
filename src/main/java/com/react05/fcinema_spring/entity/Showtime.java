package com.react05.fcinema_spring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"movie", "cinemaRoom"})
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private LocalDateTime showDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    @JsonBackReference
    private CinemaRoom cinemaRoom;

    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        SCHEDULE,
        SCREENING,
        COMPLETED,
        CANCELLED
    }
}
