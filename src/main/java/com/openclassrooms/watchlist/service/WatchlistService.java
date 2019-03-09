package com.openclassrooms.watchlist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.exception.DuplicateTitleException;
import com.openclassrooms.watchlist.repository.WatchlistRepository;

public class WatchlistService {
	
	private WatchlistRepository watchlistRepository;
	private MovieRatingService movieRatingService;

	public WatchlistService(WatchlistRepository watchlistRepository, MovieRatingService movieRatingService) {
		super();
		this.watchlistRepository = watchlistRepository;
		this.movieRatingService = movieRatingService;
	}

	public List<WatchlistItem> getWatchlistItems() {
		List<WatchlistItem> watchlistItems = watchlistRepository.getList();
		
		for (WatchlistItem watchlistItem : watchlistItems) {
			Optional<String> movieRating = movieRatingService.getMovieRating(watchlistItem.getTitle());
			if (movieRating.isPresent()) {
				watchlistItem.setRating(movieRating.get());
			}
		}
		return watchlistItems;
	}
	
	public int getWatchlistItemsSize() {
		return watchlistRepository.getList().size();
	}
	
	public void addWatchlistItem(WatchlistItem watchlistItem) throws DuplicateTitleException {
		
		if (watchlistRepository.findByTitle(watchlistItem.getTitle()).isPresent()) {
			throw new DuplicateTitleException();
		}
		watchlistRepository.addItem(watchlistItem);	
	}
}
