package com.openclassrooms.watchlist.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.exception.DuplicateTitleException;
import com.openclassrooms.watchlist.service.WatchlistService;

@Controller
public class WatchlistController {

	WatchlistService watchlistService = new WatchlistService(); 

	@GetMapping("/watchlist")
	public ModelAndView getWatchlist() {

		String viewName = "watchlist";
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("watchlistItems", watchlistService.getWatchlistItems());
		model.put("numberOfMovies", watchlistService.getWatchlistItemsSize());

		return new ModelAndView(viewName, model);
	}

	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchlistItemForm(@RequestParam(required = false) Integer id) {

		Map<String, Object> model = new HashMap<String, Object>();

		WatchlistItem watchlistItem = watchlistService.findWatchlistItemById(id);

		if (watchlistItem == null) {
			model.put("watchlistItem", new WatchlistItem());
		} else {
			model.put("watchlistItem", watchlistItem);
		}
		return new ModelAndView("watchlistItemForm", model);
	}

	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchlistItemForm(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ModelAndView("watchlistItemForm");
		}
		
		if (watchlistItem.getId() == null) {
			try {
				watchlistService.addWatchlistItem(watchlistItem);
			} catch (DuplicateTitleException e) {
				bindingResult.rejectValue("title", "", "This movie is already on your watchlist");
	            return new ModelAndView("watchlistItem");
			}			
		}else {
			watchlistService.updateWatchlistItem(watchlistItem);
		}

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/watchlist");

		return new ModelAndView(redirectView);
	}
}
