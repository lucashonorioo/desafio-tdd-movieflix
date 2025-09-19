package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    @Override
    public ReviewDTO insert(ReviewDTO reviewDTO) {
        if(reviewDTO == null){
            throw new IllegalArgumentException("A review não pode ser vazia");
        }
        Review review = new Review();
        toEntity(reviewDTO, review);

        reviewRepository.save(review);
        return fromEntity(review);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReviewDTO> findByMovie(Long movieId) {

        List<Review> list = reviewRepository.findByMovieId(movieId);
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    private void toEntity(ReviewDTO reviewDTO, Review review){
        review.setText(reviewDTO.getText());

        Movie movie = movieRepository.getReferenceById(reviewDTO.getMovieId());
        review.setMovie(movie);

        User user = authService.authenticated();
        review.setUser(user);
    }

    private ReviewDTO fromEntity(Review review){
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setText(review.getText());
        dto.setMovieId(review.getMovie().getId());

        dto.setUserId(review.getUser().getId());
        dto.setUserName(review.getUser().getName());
        dto.setUserEmail(review.getUser().getEmail());

        return dto;
    }
}
