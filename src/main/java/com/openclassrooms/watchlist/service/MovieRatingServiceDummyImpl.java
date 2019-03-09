package com.openclassrooms.watchlist.service;

import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(name = "app.env", havingValue = "dev")
@Service
public class MovieRatingServiceDummyImpl implements MovieRatingService {

	@Override
	public Optional<String> getMovieRating(String title) {
		
		return Optional.of("9.99");
	}
}
