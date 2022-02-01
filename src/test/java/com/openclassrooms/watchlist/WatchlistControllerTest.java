package com.openclassrooms.watchlist;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@RunWith(SpringRunner.class)
public class WatchlistControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testShowWatchlistForm() throws Exception {
		
		mockMvc.perform(get("/watchlistItemForm"))
		.andExpect(status().is2xxSuccessful())  // expect a successful request
		.andExpect(view().name("watchlistItemForm")) // expect the view name 
		.andExpect(model().size(1)) // expect the model size
		.andExpect(model().attributeExists("watchListItem")); // expect the model to have a ""watchlistItem" attribute
		
	}
	
	@Test
	public void testGetWatchList() throws Exception {
		
		mockMvc.perform(get("/watchlist"))
		.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("watchlist"))
		.andExpect(model().size(2))
		.andExpect(model().attributeExists("watchListItems"));
	}
	
	
	@Test
	public void testSubmitWatchListItemForm() throws Exception {
		
		mockMvc.perform(post("/watchlistItemForm"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/watchlist"));
	}
}
