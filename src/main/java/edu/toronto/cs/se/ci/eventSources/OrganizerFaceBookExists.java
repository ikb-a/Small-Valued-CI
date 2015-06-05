package edu.toronto.cs.se.ci.eventSources;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.sources.DoesURLExist;
import edu.toronto.cs.se.ci.sources.SourceFactory;

/**
 * Checks that the Facebook URL supplied as the organizer's facebook page exists.
 * @author wginsberg
 *
 */
public class OrganizerFaceBookExists extends EventSource{

	DoesURLExist urlChecker;
	
	public OrganizerFaceBookExists() {
		//urlChecker = new DoesURLExist();
		urlChecker = (DoesURLExist) SourceFactory.getSource(DoesURLExist.class);
	}
	
	@Override
	public String getName(){
		return "Organization facebook url exists";
	}
	
	@Override
	protected Integer getResponseOnline(Event e) {
		return urlChecker.getResponse(e.getOrganizer().getContactInfo().getFacebookURL());
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
