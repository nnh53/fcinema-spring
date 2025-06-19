package com.react05.fcinema_spring.repository;

import com.react05.fcinema_spring.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("SELECT m FROM Movie m " +
            "WHERE (:search IS NULL OR LOWER(m.name) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND (:fromDate IS NULL OR m.fromDate >= :fromDate) " +
            "AND (:toDate IS NULL OR m.toDate <= :toDate) " +
            "AND (:type IS NULL OR m.type = :type)")
    Page<Movie> findMoviesWithFilters(
            @Param("search") String search,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("type") String type,
            Pageable pageable
    );
}
