package edu.toronto.cs.se.ci.eventObjects;

import java.util.ArrayList;
import java.util.Collection;

public class BasicEvent implements Event {

	private ArrayList<String> guests;
	private Venue venue;
	private String organizer;
	
	public BasicEvent(){
		guests = new ArrayList<String>();
	}
	
	public BasicEvent(Venue venue, Collection<String> guestlist, String organizer){
		this.venue = venue;
		this.guests = (ArrayList<String>) guestlist;
		this.organizer = organizer;
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
