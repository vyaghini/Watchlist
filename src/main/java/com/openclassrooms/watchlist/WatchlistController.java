package com.openclassrooms.watchlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WatchlistController {
	
	List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();
	
	@GetMapping("/watchlist")
	public ModelAndView getList() {
			
			String viewName = "watchlist";
			Map<String, Object> model = new HashMap<String, Object>();
			
			watchlistItems.add(new WatchlistItem(1, "Lion King","8.5","high","Hakuna matata!"));
			watchlistItems.add(new WatchlistItem(2, "Frozen","7.5","medium","Let it go!"));
			watchlistItems.add(new WatchlistItem(3, "Cars","7.1","low","Go go go!"));
			watchlistItems.add(new WatchlistItem(4, "Wall-E","8.4","high","You are crying!"));
			
			model.put("watchlistItems", watchlistItems);
			model.put("numberOfMovies", watchlistItems.size());
			
			return new ModelAndView(viewName , model);
	}
}
