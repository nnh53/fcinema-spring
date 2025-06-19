package com.react05.fcinema_spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"movie", "room"})
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private LocalDateTime showDateTime;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private CinemaRoom room;

    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        SCHEDULE, SCREENING, COMPLETED
    }
}
