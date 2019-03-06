package com.openclassrooms.watchlist.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.openclassrooms.watchlist.domain.WatchlistItem;

@Service
public class WatchlistRepository {
	
	List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();

	public void addItem(WatchlistItem item) {
		watchlistItems.add(item);
	}
	
	public List<WatchlistItem> getList(){
		return watchlistItems;
	}
	
	public Optional<WatchlistItem> findByTitle(String title) {
		for (WatchlistItem watchlistItem : watchlistItems) {
			if (watchlistItem.getTitle().equals(title)) {
				return Optional.of(watchlistItem);
			}
		}
		return Optional.empty();
	}
}
