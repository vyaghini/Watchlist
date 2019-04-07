package com.openclassrooms.watchlist.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.watchlist.service.WatchlistService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WatchlistControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private WatchlistService watchlistService;
	
    @Test
    public void watchlistItemShouldAddWatchlistItemToMdel() throws Exception {
        
    	this.mockMvc.perform(get("/watchlistItemForm"))
    	.andExpect(model().size(1))
    	.andExpect(model().attributeExists("watchlistItem"))
    	.andExpect(view().name("watchlistItemForm"));
    }

    @Test
    public void watchlistItemFormSubmitShouldRedirectTo() throws Exception {
        
    	this.mockMvc.perform(post("/watchlistItemForm")
    			.param("title", "title")
    			.param("Rating", "10")
    			.param("priority", "H"))
    	.andExpect(status().is3xxRedirection())
    	.andExpect(redirectedUrl("/watchlist"));
    }
}
