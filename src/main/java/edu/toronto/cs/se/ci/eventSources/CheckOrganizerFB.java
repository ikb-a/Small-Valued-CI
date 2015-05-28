package edu.toronto.cs.se.ci.eventSources;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.sources.FaceBookProfileCheck;

public class CheckOrganizerFB extends EventSource {

	private FaceBookProfileCheck facebook;
	
	public CheckOrganizerFB(){
		facebook = new FaceBookProfileCheck();
	}

	@Override
	public String getName(){
		return "Organizer exists (FB)";
	}
	
	@Override
	public Integer getResponseOnline(Event e) {
		try{
			facebook.allowNonPeople = false;
			return facebook.getResponse(e.getOrganizerName());
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
