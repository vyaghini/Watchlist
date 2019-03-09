package com.openclassrooms.watchlist.service;

import java.util.Optional;

public interface MovieRatingService {

	Optional<String> getMovieRating(String title);

}