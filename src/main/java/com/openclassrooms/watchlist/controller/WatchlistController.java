package com.openclassrooms.watchlist.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.exception.DuplicateTitleException;
import com.openclassrooms.watchlist.service.WatchlistService;
import com.openclassrooms.watchlist.validation.ValidationOrder;

@Controller
public class WatchlistController {
	
	private WatchlistService watchlistService;
	
	@Autowired
	public WatchlistController(WatchlistService watchlistService) {
		super();
		this.watchlistService = watchlistService;
	}

	@GetMapping("/watchlist")
	public ModelAndView getMovieList() {
		
		String viewName = "watchlist";
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("watchlistItems", watchlistService.getWatchlistItems());
		model.put("numberOfMovies", watchlistService.getWatchlistItemsSize());
		
		return new ModelAndView(viewName , model);
	}
	
	@GetMapping("/watchlistItem")
	public ModelAndView showWatchlistItem() {
		
		String viewName = "watchlistItem";
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("watchlistItem", new WatchlistItem());
	
		return new ModelAndView(viewName , model);
	}
	
	@PostMapping("/watchlistItem")
	public ModelAndView submitWatchlistItemForm(@Validated(ValidationOrder.class) WatchlistItem watchlistItem, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
            return new ModelAndView("watchlistItem");
        }
		
		try {
			watchlistService.addWatchlistItem(watchlistItem);
		} catch (DuplicateTitleException e) {
			bindingResult.rejectValue("title", "", "This movie is already on your watchlist");
            return new ModelAndView("watchlistItem");
		}
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/watchlist");
		
		return new ModelAndView(new RedirectView("/watchlist"));
	}
}
