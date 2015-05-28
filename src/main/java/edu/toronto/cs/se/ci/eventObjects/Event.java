package edu.toronto.cs.se.ci.eventObjects;

import java.util.ArrayList;

public abstract class Event {

	abstract public ArrayList<String> getGuestNames();
	
	abstract public Venue getVenue();

	abstract public String getOrganizerName();
	
	abstract public String getTitle();
	
	public String getID(){
		return getTitle();
	}
}
