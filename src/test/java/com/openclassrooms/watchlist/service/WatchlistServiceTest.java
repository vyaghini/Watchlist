package com.openclassrooms.watchlist.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.repository.WatchlistRepository;

@RunWith(MockitoJUnitRunner.class)
public class WatchlistServiceTest {
	
	@InjectMocks
	private WatchlistService watchlistService;
	
	@Mock
	private WatchlistRepository watchlistRepositoryMock;
	
	@Mock
	private MovieRatingService movieRatingServiceMock;
	
	@Test
	public void getWatchlistItemsShoiuldReturnAllItems() {
		
		WatchlistItem item1 = new WatchlistItem("Up", "7.5", "HIGH","", "");
		WatchlistItem item2 = new WatchlistItem("Se7en", "9.9", "MEDIUM","", "");
		List<WatchlistItem> mockResult = Arrays.asList(item1,item2);
		when(watchlistRepositoryMock.getList()).thenReturn(mockResult);
		
		List<WatchlistItem> watchlistItems = watchlistService.getWatchlistItems();
		
		assertEquals(2, watchlistItems.size());
		assertEquals("Up", watchlistItems.get(0).getTitle());
		assertEquals("Se7en", watchlistItems.get(1).getTitle());
	}
	
	@Test
	public void getWatchlistItemsShouldReturnMovieRatingProvidedByOMDB() {
		
		WatchlistItem item1 = new WatchlistItem("Up", "7.5", "HIGH","", "");
		List<WatchlistItem> mockResult = Arrays.asList(item1);
		when(watchlistRepositoryMock.getList()).thenReturn(mockResult);
		when(movieRatingServiceMock.getMovieRating(any(String.class))).thenReturn(Optional.of("10"));
		
		List<WatchlistItem> watchlistItems = watchlistService.getWatchlistItems();

		assertEquals("10", watchlistItems.get(0).getRating());
	}
}
