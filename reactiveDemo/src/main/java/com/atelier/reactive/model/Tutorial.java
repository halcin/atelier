package com.atelier.reactive.model;

import org.springframework.data.annotation.Id;

public class Tutorial {

	@Id
	private int id;

	private String title;

	private String description;

	private boolean published;
	
	private int rating;
	
	public Tutorial() {

	}

	public Tutorial(String title, String description, boolean published,int rating) {
		this.title = title;
		this.description = description;
		this.published = published;
		this.rating = rating;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
	}
}
