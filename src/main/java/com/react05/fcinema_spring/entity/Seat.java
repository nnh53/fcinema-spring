package com.react05.fcinema_spring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seat")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "cinemaRoom")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    @JsonBackReference
    private CinemaRoom cinemaRoom;

    @Column(name = "seat_row")
    private String row;
    @Column(name = "seat_column")
    private String column;
    private String name;
    @Enumerated(EnumType.STRING)
    private SeatType type;
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        AVAILABLE, BOOKED, RESERVED, MAINTENANCE
    }
    public enum SeatType {
        VIP, REGULAR, COUPLE, PATH
    }
}
