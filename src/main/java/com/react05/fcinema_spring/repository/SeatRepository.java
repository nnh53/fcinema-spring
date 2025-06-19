package com.react05.fcinema_spring.repository;

import com.react05.fcinema_spring.entity.Seat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
  List<Seat> findByCinemaRoom_Id(Integer roomId);
}
