package com.react05.fcinema_spring.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "seats") // Exclude seats from toString() to prevent infinite recursion
public class CinemaRoom {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private int roomNumber;
  private String type;
  private double fee;
  private int capacity;

  @Enumerated(EnumType.STRING)
  private Status status;

  private int width;
  private int length;

  @OneToMany(mappedBy = "cinemaRoom", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Seat> seats;

  public enum Status {
    ACTIVE,
    MAINTENANCE,
    CLOSED
  }
}
