package com.openclassrooms.watchlist.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.openclassrooms.watchlist.validation.ClassValidations;
import com.openclassrooms.watchlist.validation.FieldValidations;
import com.openclassrooms.watchlist.validation.GoodMovie;
import com.openclassrooms.watchlist.validation.Priority;

@GoodMovie(groups= {ClassValidations.class})
public class WatchlistItem {
	
	Integer id;
	
	@NotBlank( message="Please enter the title" , groups= {FieldValidations.class})
	private String title;
	
	@Pattern(regexp = "[0-9]+(\\.[0-9]?)?", message="Rating should be a number with 1 decimal place", groups= {FieldValidations.class})
	@Min(value=5 , message="Please enter a rating of min 5.0", groups= {FieldValidations.class}) 
	@Max(value=10, message="Please enter a rating of max 10.0", groups= {FieldValidations.class})
	private String rating;  

	@Priority(groups= {FieldValidations.class})
	private String priority;
	
	private String comment; 
	
	private String status;
	
	public WatchlistItem() {
		super();
	}

	public WatchlistItem(String title, String rating, String priority, String comment, String status) {
		super();
		this.title = title;
		this.rating = rating;
		this.priority = priority;
		this.comment = comment;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
