package com.techelevator.clebrews.model;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

public class Beer {

	private Long id;
	
	@NotBlank
	private String name;
	
	private float abv;
	
	private int ibu;
	@NotBlank
	private String type;
	@NotBlank
	private String info;
	
	private String imgUrl;
	
	private Long breweryId;
	
	private BigDecimal rating;
	
	
	private boolean isActive = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getAbv() {
		return abv;
	}

	public void setAbv(float abv) {
		this.abv = abv;
	}

	public int getIbu() {
		return ibu;
	}

	public void setIbu(int ibu) {
		this.ibu = ibu;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getBreweryId() {
		return breweryId;
	}

	public void setBreweryId(Long breweryId) {
		this.breweryId = breweryId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}
	
	public int getStarRating() {
		int starRating = 0;
		
		if (rating != null) {
			starRating = rating.intValue();
		}
		return starRating;
	}
	
}
