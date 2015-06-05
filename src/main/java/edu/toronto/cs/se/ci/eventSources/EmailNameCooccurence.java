package edu.toronto.cs.se.ci.eventSources;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.sources.GoogleCoocurrence;
import edu.toronto.cs.se.ci.sources.SourceFactory;

/**
 * An event source for checking that an event organizer's email and name
 * co-occur in google searches.
 * @author wginsberg
 *
 */
public class EmailNameCooccurence extends EventSource {
	
	static protected int MAX_RESULTS_TO_CHECK = 10;
	
	@Override
	public String getName(){
		return "Email-Name Coocurence";
	}
	
	/**
	 * Returns 1 if a google search for the event organizer's email
	 * address returns results which contain the event organizer's name.
	 */
	@Override
	protected Integer getResponseOnline(Event e) {
		
		GoogleCoocurrence coocurrence = (GoogleCoocurrence) SourceFactory.getSource(GoogleCoocurrence.class);

		String email = e.getOrganizer().getContactInfo().getEmailAddress();
		String name = e.getOrganizer().getName();
		String [] emailAndName = {email, name};
		
		return coocurrence.getResponse(emailAndName);
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
