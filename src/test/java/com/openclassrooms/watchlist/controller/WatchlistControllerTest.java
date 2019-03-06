package com.openclassrooms.watchlist.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WatchlistControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
    @Test
    public void watchlistItemShouldAddWatchlistItemToMdel() throws Exception {
        
    	this.mockMvc.perform(get("/watchlistItem"))
    	.andExpect(model().size(1))
    	.andExpect(model().attributeExists("watchlistItem"))
    	.andExpect(view().name("watchlistItem"));
    }

    @Test
    public void watchlistItemFormSubmitShouldRedirectTo() throws Exception {
        
    	this.mockMvc.perform(post("/watchlistItem"))
    	.andExpect(status().is3xxRedirection())
    	.andExpect(redirectedUrl("/watchlist"));
    }
    
}
