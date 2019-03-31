package com.openclassrooms.watchlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WatchlistController {
	
	List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();
	private static int index=1;
	
	
	@GetMapping("/watchlist")
	public ModelAndView getWatchlist() {
			
			String viewName = "watchlist";
			Map<String, Object> model = new HashMap<String, Object>();

			model.put("watchlistItems", watchlistItems);
			model.put("numberOfMovies", watchlistItems.size());
			
			return new ModelAndView(viewName , model);
	}
	
	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchlistItemForm(@RequestParam(required=false) Integer id) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		WatchlistItem watchlistItem = findWatchlistItemById(id);
		
		if (watchlistItem == null) {
			model.put("watchlistItem", new WatchlistItem());
		} else {
			model.put("watchlistItem", watchlistItem);
		}
		return new ModelAndView("watchlistItemForm" , model);
	}
	
	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchlistItemForm(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {
	
		if (bindingResult.hasErrors()) {
            return new ModelAndView("watchlistItemForm");
        }
		
		if (watchlistItem.getId()==null) {
			watchlistItem.setId(index++);
			watchlistItems.add(watchlistItem);
		}else {
			WatchlistItem toBeUpdated = findWatchlistItemById(watchlistItem.getId());
			toBeUpdated.setComment(watchlistItem.getComment());
			toBeUpdated.setPriority(watchlistItem.getPriority());
			toBeUpdated.setRating(watchlistItem.getRating());
			toBeUpdated.setTitle(watchlistItem.getTitle());
		}
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/watchlist");
		
		return new ModelAndView(redirectView);
	}
	
	private WatchlistItem findWatchlistItemById(Integer id) {
		
		for (WatchlistItem watchlistItem : watchlistItems) {
			if (watchlistItem.getId().equals(id)) {
				return watchlistItem;
			}
		}
		return null;
	}
}
