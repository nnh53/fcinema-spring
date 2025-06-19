package com.react05.fcinema_spring.repository;

import com.react05.fcinema_spring.entity.CinemaRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRoomRepository extends JpaRepository<CinemaRoom, Integer> {}
