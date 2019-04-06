package com.openclassrooms.watchlist.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.openclassrooms.watchlist.domain.WatchlistItem;

public class WatchlistRepository {
	
	List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();
	
	int index = 1;

	public void addItem(WatchlistItem item) {
		item.setId(index++);
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
	
	public WatchlistItem findById(Integer id) {
		for (WatchlistItem watchlistItem : watchlistItems) {
			if (watchlistItem.getId().equals(id)) {
				return watchlistItem;
			}
		}
		return null;
	}
}
