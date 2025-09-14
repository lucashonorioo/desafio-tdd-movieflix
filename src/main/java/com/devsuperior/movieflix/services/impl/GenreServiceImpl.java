package com.devsuperior.movieflix.services.impl;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.services.GenreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional
    @Override
    public List<GenreDTO> findAll() {
        List<Genre> genres = genreRepository.findAll();

        return genres.stream().map( g -> {
            GenreDTO genreDTO = new GenreDTO();
            genreDTO.setId(g.getId());
            genreDTO.setName(g.getName());
            return genreDTO;
        }).collect(Collectors.toList());
    }
}
