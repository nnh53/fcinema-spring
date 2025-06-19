package com.react05.fcinema_spring.repository;

import com.react05.fcinema_spring.entity.CinemaRoom;
import com.react05.fcinema_spring.entity.Movie;
import com.react05.fcinema_spring.entity.Showtime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {
    List<Showtime> findByMovie(Movie movie);

    List<Showtime> findByRoom(CinemaRoom room);

    @Query("SELECT s FROM Showtime s WHERE s.room = :room AND " +
            "((s.showDateTime <= :endTime AND s.endDateTime >= :startTime) OR " +
            "(s.showDateTime <= :endTime AND s.showDateTime >= :startTime) OR " +
            "(s.endDateTime <= :endTime AND s.endDateTime >= :startTime))")
    List<Showtime> findConflictingShowtimes(
            @Param("room") CinemaRoom room,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);

    @Query("SELECT s FROM Showtime s " +
            "WHERE (:movieId IS NULL OR s.movie.id = :movieId) " +
            "AND (:roomId IS NULL OR s.room.id = :roomId) " +
            "AND (:startDate IS NULL OR s.showDateTime >= :startDate) " +
            "AND (:endDate IS NULL OR s.showDateTime <= :endDate) " +
            "AND (:status IS NULL OR s.status = :status)")
    Page<Showtime> findWithFilters(
            @Param("movieId") Integer movieId,
            @Param("roomId") Integer roomId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("status") Showtime.Status status,
            Pageable pageable);

}
