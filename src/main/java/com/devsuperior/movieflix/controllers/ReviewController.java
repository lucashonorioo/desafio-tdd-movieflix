package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PreAuthorize("hasAnyRole('ROLE_MEMBER')")
    @PostMapping
    ResponseEntity<ReviewDTO> insert(@Valid @RequestBody ReviewDTO reviewDTO){
        reviewDTO = reviewService.insert(reviewDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(reviewDTO.getId()).toUri();
        return ResponseEntity.created(location).body(reviewDTO);
    }
}
