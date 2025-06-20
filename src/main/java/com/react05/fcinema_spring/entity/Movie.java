package com.react05.fcinema_spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "showtimes")
public class Movie {
    @OneToMany
    @JoinColumn(name = "movie_id")
    List<Showtime> showtimes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
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
    private String poster;
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        ACTIVE,
        INACTIVE,
        UPCOMING
    }
}
