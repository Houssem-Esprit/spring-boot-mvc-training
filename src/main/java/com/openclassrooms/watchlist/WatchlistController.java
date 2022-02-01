package com.openclassrooms.watchlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WatchlistController {
	
	private List<WatchListItem> watchListItems = new ArrayList<WatchListItem>();
	private static int index = 1; 
	

	@GetMapping("/watchlist")
	public ModelAndView getWatchlist() {
				
		// this is represent the view name which will be sent to the viewResolver
		String viewName = "watchlist";
		// this is represent the model wich is a Map holds the data needed for a specific view
		Map<String, Object> model = new HashMap<String,Object>();
		
		model.put("watchListItems", watchListItems);
		model.put("numberOfMovies", watchListItems.size());
		
		// return the combined elements using the ModekAndView class
		return new ModelAndView(viewName, model);
	}
	
	
	
	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchlistItemForm(@RequestParam(required = false) Integer id) {
		
		String viewName = "watchlistItemForm";
		Map<String, Object> model = new HashMap<String,Object>();
		
		WatchListItem watchListItem = findWatchListItemById(id);
		
		if(watchListItem == null) {
			model.put("watchListItem", new WatchListItem());
		}else {
			model.put("watchListItem", watchListItem);
		}
		
		return new ModelAndView(viewName, model);
	}
	
	
	
	
	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchlistItemForm(@Valid WatchListItem watchLsitItem, BindingResult bindingResult) {
		
		// BindingResult: contains the validation result (if validation fails it will bind the errors to the template)
		if(bindingResult.hasErrors()) {
			return new ModelAndView("watchlistItemForm");
		}
		
		WatchListItem existingItem = findWatchListItemById(watchLsitItem.getId());
		
		if(existingItem == null) {
			watchLsitItem.setId(index++);
			watchListItems.add(watchLsitItem);
		}else {
			existingItem.setTitle(watchLsitItem.getTitle());
			existingItem.setPriority(watchLsitItem.getPriority());
			existingItem.setRating(watchLsitItem.getRating());
			existingItem.setComment(watchLsitItem.getComment());
		}
		
		
		// we are using the RedirectView class to redirect user into another URL 
		RedirectView redirectView = new RedirectView();
		
		redirectView.setUrl("/watchlist");
		
		return new ModelAndView(redirectView);
	}
	
	
	
	private WatchListItem findWatchListItemById(Integer id) {
		for(WatchListItem watchListItem : watchListItems) {
			if(watchListItem.getId().equals(id)) {
				return watchListItem;
			}
		}
		return null;

	}
}
