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
	
	private List<WatchListItem> watchListItems = new ArrayList<WatchListItem>();
	private static int index = 1;
	

	@GetMapping("/watchlist")
	public ModelAndView getWatchlist() {
		
		watchListItems.clear();
		watchListItems.add(new WatchListItem("Lion King","8.5","high","Hakuna Matata",index++));
		watchListItems.add(new WatchListItem("Frozen","7.5","medium","Let's go!",index++));
		watchListItems.add(new WatchListItem("Cars","7.1","low","VROOM!",index++));
		watchListItems.add(new WatchListItem("Wall-E","8.5","high","You are crying!",index++));
		
		
		// this is represent the view name which will be sent to the viewResolver
		String viewName = "watchlist";
		// this is represent the model wich is a Map holds the data needed for a specific view
		Map<String, Object> model = new HashMap<String,Object>();
		
		model.put("watchListItems", watchListItems);
		model.put("numberOfMovies", watchListItems.size());
		
		// return the combined elements using the ModekAndView class
		return new ModelAndView(viewName, model);
	}
}
