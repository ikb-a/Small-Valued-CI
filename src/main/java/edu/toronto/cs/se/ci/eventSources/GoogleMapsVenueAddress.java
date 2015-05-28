package edu.toronto.cs.se.ci.eventSources;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.sources.GMapsEstablishmentHasAddress;

/**
 * Wrapper for the functionality of the GMapsEstablishmentHasAddress source.
 * @author will
 *
 */
public class GoogleMapsVenueAddress extends EventSource {

	private GMapsEstablishmentHasAddress googleMaps;
	
	public GoogleMapsVenueAddress() {
		googleMaps = new GMapsEstablishmentHasAddress();
	}

	@Override
	public String getName(){
		return "Venue address cross validated (GMaps + GPlaces)";
	}
	
	@Override
	public Integer getResponseOnline(Event e){
		
		try{
			return googleMaps.getResponse(e.getVenue());
		}
		catch (Exception ex){
			return -1;
		}
	}

	@Override
	public Expenditure[] getCost(Event args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void getTrust(Event args, Optional<Integer> value) {
		// TODO Auto-generated method stub
		return null;
	}

}
