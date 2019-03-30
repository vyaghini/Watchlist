package com.openclassrooms.watchlist;

public class WatchlistItem {

	int id;
	String title, rating, priority, comment;
	
	
	public WatchlistItem() {
	}

	public WatchlistItem(int id, String title, String rating, String priority, String comment) {
		super();
		this.id = id;
		this.title = title;
		this.rating = rating;
		this.priority = priority;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
}
