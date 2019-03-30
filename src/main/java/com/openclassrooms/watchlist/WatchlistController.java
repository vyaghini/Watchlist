package com.openclassrooms.watchlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WatchlistController {
	
	List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();
	
	@GetMapping("/watchlist")
	public ModelAndView getWatchlist() {
			
			String viewName = "watchlist";
			Map<String, Object> model = new HashMap<String, Object>();
			
			model.put("watchlistItems", watchlistItems);
			model.put("numberOfMovies", watchlistItems.size());
			
			return new ModelAndView(viewName , model);
	}
	
	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchlistItemForm() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("watchlistItem", new WatchlistItem());
	
		return new ModelAndView("watchlistItemForm" , model);
	}
	
	@PostMapping("/watchlistItemForm")
	public RedirectView submitWatchlistItemForm(@ModelAttribute WatchlistItem watchlistItem) {
		
		watchlistItems.add(watchlistItem);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/watchlist");
		
		return redirectView;
	}
}
