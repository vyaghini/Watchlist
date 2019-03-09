package com.openclassrooms.watchlist.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@ConditionalOnProperty(name = "app.env", havingValue = "prod")
@Service
public class MovieRatingServiceLiveImpl implements MovieRatingService {
	
	private String apiUrl = "http://www.omdbapi.com/?apikey=cc9bf9ef&t=";	
	
	@Override
	public Optional<String> getMovieRating(String title) { 
		
		RestTemplate template = new RestTemplate();
		
		ResponseEntity<String> response = template.getForEntity(apiUrl + title , String.class);
		
		try {
			JsonNode node = new ObjectMapper().readTree(response.getBody()).path("imdbRating");
			if (node.isMissingNode()) {
				System.out.println("missing node");
			}
			return Optional.ofNullable(node.asText());
		} catch (IOException e) {
			System.out.println("ERROR! IOException happened!");
			return Optional.empty();
		}
	}
}

