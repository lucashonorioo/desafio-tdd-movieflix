package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.services.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/genres")
public class GenreControllers {

    private final GenreService genreService;

    public GenreControllers(GenreService genreService) {
        this.genreService = genreService;
    }

    @PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
    @GetMapping
    ResponseEntity<List<GenreDTO>> findAll(){
        List<GenreDTO> genreDTOS = genreService.findAll();
        return ResponseEntity.ok().body(genreDTOS);
    }
}
