package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
    @GetMapping("/{id}")
    ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id){
        MovieDetailsDTO  movieDetailsDTO = movieService.findById(id);
        return ResponseEntity.ok().body(movieDetailsDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
    @GetMapping
    ResponseEntity<Page<MovieDetailsDTO>> findByGenre(@RequestParam(value = "genreId", defaultValue = "0") Long genreId, Pageable pageable){
        Page<MovieDetailsDTO> movieDetailsDTOS = movieService.findByGenre(genreId, pageable);
        return ResponseEntity.ok().body(movieDetailsDTOS);
    }
}
