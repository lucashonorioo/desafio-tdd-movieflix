package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {

    MovieDetailsDTO findById(Long id);
    Page<MovieDetailsDTO> findByGenre(Long genreId, Pageable pageable);
}
