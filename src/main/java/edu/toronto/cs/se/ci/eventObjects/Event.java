package edu.toronto.cs.se.ci.eventObjects;

import java.util.ArrayList;

public interface Event {

	public ArrayList<String> getGuestNames();
	
	public Venue getVenue();

	public String getOrganizerName();
}
