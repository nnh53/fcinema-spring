package com.react05.fcinema_spring.repository;

import com.react05.fcinema_spring.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByCinemaRoom_Id(Integer roomId);
}

