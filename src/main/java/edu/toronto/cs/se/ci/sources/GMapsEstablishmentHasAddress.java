package edu.toronto.cs.se.ci.sources;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.toronto.cs.se.ci.realWorldObjects.Establishment;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.UnknownException;
import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.playground.data.Address;
import edu.toronto.cs.se.ci.playground.sources.GMapsGeocode;
import edu.toronto.cs.se.ci.utils.BasicSource;

public class GMapsEstablishmentHasAddress extends BasicSource<Establishment, Integer, Void> {

	static private GMapsGeocode googleMaps;
	
	//this holds the search key, so we won't allow confusion with it being static
	private NearbySearch nearby;
	
	public GMapsEstablishmentHasAddress() {
		
		if (googleMaps == null){
			googleMaps = new GMapsGeocode();
		}

		nearby = new NearbySearch(null);
	}

	@Override
	public Integer getResponse(Establishment e) {
		
		//find the cordinates of the establishment
		List<Double> coordinates;
		try {
			coordinates = getCoordinates(e.getAddress());
		} catch (UnknownException e1) {
			return -1;
		}
		//if the coordinates are null this means the place does not exist
		if (coordinates == null){
			return 0;
		}
		
		//find places near to the coordinates
		ArrayList<String> places;
		try {
			places = placesNearTo(coordinates, e.getName());
		} catch (UnknownException e1) {
			return -1;
		}
		
		//return true if we found the name of the establishment near the establishment's address
		if (places.contains(e.getName())){
			return 1;
		}
		else{
			return 0;
		}
		
	}
	
	/**
	 * Return the location coordinates of an address from Google Maps
	 */
	public List<Double> getCoordinates(Address a) throws UnknownException{
		
		//get the json data of the address
		JSONObject json = googleMaps.getResponse(a);
		
		//get the location
		JSONArray results = json.getJSONArray("results");
		
		//check for empty result
		if (results.length() == 0){
			return null;
		}
		
		JSONObject firstResult = results.getJSONObject(0);
		JSONObject geometry = firstResult.getJSONObject("geometry");
		JSONObject location = geometry.getJSONObject("location");
		
		//get the coordinates to return
		List<Double> coords = new ArrayList<Double>();
		coords.add(0, location.getDouble("lat"));
		coords.add(1, location.getDouble("lng"));
		
		return coords;
	}
	
	/**
	 * Returns an array of names of places
	 * @param ds - the point to search around
	 * @param searchFor - the search key
	 * @return - list of names of places
	 * @throws UnknownException
	 */
	public ArrayList<String> placesNearTo(List<Double> coordinates, String searchFor) throws UnknownException{
		
		//get the search results
		nearby.setSearchKey(searchFor);
		JSONObject nearbyResponse = nearby.getResponse(coordinates);
		JSONArray results = nearbyResponse.getJSONArray("results");
		
		//get the names
		ArrayList<String> names = new ArrayList<String>();
		for (int i = 0; i < results.length(); i++){
			JSONObject result = results.getJSONObject(i);
			String name = result.getString("name");
			names.add(name);
		}
		
		return names;
	}
	
	
	@Override
	public Expenditure[] getCost(Establishment arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void getTrust(Establishment arg0, Optional<Integer> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
