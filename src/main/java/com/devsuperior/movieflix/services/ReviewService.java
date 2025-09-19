package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    ReviewDTO insert(ReviewDTO reviewDTO);
    List<ReviewDTO> findByMovie(Long movieId);
}
