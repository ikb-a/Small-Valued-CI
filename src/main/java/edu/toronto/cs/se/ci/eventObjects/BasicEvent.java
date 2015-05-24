package edu.toronto.cs.se.ci.eventObjects;

import java.util.ArrayList;

public class BasicEvent implements Event {

	private ArrayList<String> guests;
	private Venue venue;
	private String organizer;
	
	public BasicEvent(){
		guests = new ArrayList<String>();
	}
	
	public void setVenue(Venue v){
		venue = v;
	}
	
	public void setOrganizer(String o){
		organizer = o;
	}
	
	public void addGuest(String g){
		guests.add(g);
	}

	public ArrayList<String> getGuestNames() {
		return guests;
	}

	public Venue getVenue() {
		return venue;
	}

	public String getOrganizerName() {
		return organizer;
	}

}
