package com.openclassrooms.watchlist.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MovieRatingService {
		
	private String apiUrl = "http://www.omdbapi.com/?apikey=cc9bf9ef&t=";	
	
	public Optional<String> getMovieRating(String title) { 
		
		RestTemplate template = new RestTemplate();
		try {
			ResponseEntity<ObjectNode> response = template.getForEntity(apiUrl + title , ObjectNode.class);
			
			ObjectNode jsonObject = response.getBody();
			JsonNode node = jsonObject.path("imdbRating");
			
			if (node.isMissingNode()) {
				return Optional.empty();
			}
			return Optional.ofNullable(node.asText());
			
		} catch (Exception e) {
			return Optional.empty();
		}
	}
}
