package com.openclassrooms.watchlist.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.watchlist.controller.WatchlistController;
import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.exception.DuplicateTitleException;
import com.openclassrooms.watchlist.repository.WatchlistRepository;

public class WatchlistService {
		
	private WatchlistRepository watchlistRepository;
	private MovieRatingService movieRatingService;

	@Autowired
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
	
	public WatchlistItem findWatchlistItemById(Integer id) {
		return watchlistRepository.findById(id);
		
	}

	public void updateWatchlistItem(WatchlistItem inputWatchlistItem) {
		WatchlistItem currentWatchlistItem = 
				watchlistRepository.findById(inputWatchlistItem.getId());
		
		currentWatchlistItem.setTitle(inputWatchlistItem.getTitle());
		currentWatchlistItem.setComment(inputWatchlistItem.getComment());
		currentWatchlistItem.setRating(inputWatchlistItem.getRating());
		currentWatchlistItem.setPriority(inputWatchlistItem.getPriority());
	}
}
