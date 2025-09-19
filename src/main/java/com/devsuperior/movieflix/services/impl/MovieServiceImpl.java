package com.devsuperior.movieflix.services.impl;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public MovieDetailsDTO findById(Long id) {
        if(id == null || id <= 0){
            throw new RuntimeException("O id não deve ser nulo, e ser maior que 0");
        }
        Movie movie = movieRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Esse filme não existe"));

        MovieDetailsDTO movieDetailsDTO = new MovieDetailsDTO();
        toDto(movieDetailsDTO, movie);
        return movieDetailsDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MovieDetailsDTO> findByGenre(Long genreId, Pageable pageable) {
        if(genreId < 0){
            throw new RuntimeException("O id não deve ser negativo");
        }
        Page<Movie> movies = movieRepository.findByGenre(genreId, pageable);

        return movies.map( m -> {
            MovieDetailsDTO dto = new MovieDetailsDTO();
            toDto(dto, m);
            return dto;
        });
    }

    private void toDto(MovieDetailsDTO dto, Movie movie){

        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setSubTitle(movie.getSubTitle());
        dto.setYear(movie.getYear());
        dto.setImgUrl(movie.getImgUrl());
        dto.setSynopsis(movie.getSynopsis());

        Genre genre = movie.getGenre();
        if(genre != null){
            GenreDTO genreDTO = new GenreDTO();
            genreDTO.setId(genre.getId());
            genreDTO.setName(genre.getName());

        dto.setGenre(genreDTO);
        }
    }

}
