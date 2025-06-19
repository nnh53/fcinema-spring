package com.react05.fcinema_spring.controller;

import com.react05.fcinema_spring.model.request.MovieAndShowtime.MovieRequest;
import com.react05.fcinema_spring.model.request.MovieAndShowtime.MovieUpdateRequest;
import com.react05.fcinema_spring.model.response.MovieAndShowtime.MovieResponse;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.PageResponse;
import com.react05.fcinema_spring.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<ApiResponse<MovieResponse>> create(@Valid @RequestBody MovieRequest request){
        return ResponseEntity.ok(movieService.createMovie(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MovieResponse>> get(@PathVariable Integer id) {
        return ResponseEntity.ok(movieService.getMovie(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MovieResponse>>> getAll() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<MovieResponse>>> searchMovies(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate,
            @RequestParam(value = "type", required = false) String type) {
        return ResponseEntity.ok(movieService.getAllMovies(pageNo, pageSize, search, fromDate, toDate, type));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MovieResponse>> update(@PathVariable Integer id, @Valid @RequestBody MovieUpdateRequest request) {
        return ResponseEntity.ok(movieService.updateMovie(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(movieService.deleteMovie(id));
    }
}
