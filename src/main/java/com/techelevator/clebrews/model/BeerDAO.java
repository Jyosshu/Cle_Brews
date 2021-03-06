package com.techelevator.clebrews.model;

import java.util.List;

public interface BeerDAO {
	public List<Beer> getAllBeer();
	
	public Beer getBeerByName(String name);
	
	public List<Beer> getBeerByBrewery(long breweryId);
	
	public Beer getBeerById(Long id);
	
	public boolean searchForBeerByName(String name);
	
	public void saveBeer(Beer newBeer);

	public List<Beer> getAllBeerByBrewery(Long breweryId); // This is only so that a Brewery can update the staus of its beers
	
	public void updateActiveBeerByBrewery(Long beerId, boolean isActive);
	
	public void removeBeer(Long beerId);
	
	public void updateBeerInfo(String name, float abv, int ibu, String type, String info, String imgUrl,Long beerId);
	
	
}
