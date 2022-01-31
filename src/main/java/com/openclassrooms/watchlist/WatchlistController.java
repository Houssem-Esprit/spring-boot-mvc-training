package com.openclassrooms.watchlist;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WatchlistController {

	@GetMapping("/watchlist")
	public ModelAndView getWatchlist() {
		// this is represent the view name which will be sent to the viewResolver
		String viewName = "watchlist";
		// this is represent the model wich is a Map holds the data needed for a specific view
		Map<String, Object> model = new HashMap<String,Object>();
		model.put("numberOfMovies", "123");
		
		// return the combined elements using the ModekAndView class
		return new ModelAndView(viewName, model);
	}
}
