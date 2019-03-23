package com.openclassrooms.watchlist.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.exception.DuplicateTitleException;
import com.openclassrooms.watchlist.service.WatchlistService;
import com.openclassrooms.watchlist.validation.ValidationOrder;

@Controller
public class WatchlistController {
	
	Logger logger = LoggerFactory.getLogger(WatchlistController.class);
	
	private WatchlistService watchlistService;
	
	@Autowired
	public WatchlistController(WatchlistService watchlistService) {
		super();
		this.watchlistService = watchlistService;
	}

	@GetMapping("/watchlist")
	public ModelAndView getMovieList() {
		
		logger.info("GET /watchlist called");
		
		String viewName = "watchlist";
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("watchlistItems", watchlistService.getWatchlistItems());
		model.put("numberOfMovies", watchlistService.getWatchlistItemsSize());
		
		return new ModelAndView(viewName , model);
	}
	
	@GetMapping("/watchlistItem")
	public ModelAndView showWatchlistItemForm(@RequestParam(required=false) Integer id) {
		
		logger.info("GET /watchlistItem called, id= "+id);
		
		String viewName = "watchlistItem";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		Optional<WatchlistItem> watchlistItemOptional = watchlistService.findWatchlistItemById(id);
		if (watchlistItemOptional.isPresent()) {
			model.put("watchlistItem", watchlistItemOptional.get());
		}else {
			model.put("watchlistItem", new WatchlistItem());
		}
	
		return new ModelAndView(viewName , model);
	}
	
	@PostMapping("/watchlistItem")
	public ModelAndView submitWatchlistItemForm(@Validated(ValidationOrder.class) WatchlistItem watchlistItem, BindingResult bindingResult) {

		logger.info("POST /watchlistItem called");
		
		if (bindingResult.hasErrors()) {
            return new ModelAndView("watchlistItem");
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
		
		return new ModelAndView(new RedirectView("/watchlist"));
	}
}
