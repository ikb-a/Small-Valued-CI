package edu.toronto.cs.se.ci.eventSources;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.helpers.MalformedInputException;
import edu.toronto.cs.se.ci.helpers.ParsePhoneNumber;
import edu.toronto.cs.se.ci.sources.GoogleCoocurrence;
import edu.toronto.cs.se.ci.sources.SourceFactory;

/**
 * Checks that the event organizer's phone number area code matches
 * the location of the event.
 * @author wginsberg
 *
 */
public class AreaCodeValid extends EventSource {

	public String getName(){
		return "Area-code-valid";
	}
	
	@Override
	protected Integer getResponseOnline(Event e) {
		
		//try to extract an area code
		String phoneNumber = e.getOrganizer().getContactInfo().getPhoneNumber();
		String areaCode;
		try{
			areaCode = ParsePhoneNumber.getAreaCode(phoneNumber);
		}
		catch (MalformedInputException ex){
			return 0;
		}
		
		//do a coocurrence search for the location of the event and the area code
		String searchFor = String.format("%s area code", e.getVenue().getAddress().getCity());
		GoogleCoocurrence coocur = (GoogleCoocurrence) SourceFactory.getSource(GoogleCoocurrence.class);
		String [] input = {searchFor, areaCode};
		return coocur.getResponse(input);
		
	}

	@Override
	public Expenditure[] getCost(Event args) throws Exception {

		return null;
	}

	@Override
	public Void getTrust(Event args, Optional<Integer> value) {
		// TODO Auto-generated method stub
		return null;
	}

}
