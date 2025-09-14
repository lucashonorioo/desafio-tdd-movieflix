package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    List<GenreDTO> findAll();
}
