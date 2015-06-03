package edu.toronto.cs.se.ci.eventObjects;

public class EventOrganizer {

	private String name;
	private EventContact contact;
	
	public EventOrganizer(){
		name = "";
		contact = null;
	}
	
	public String getName(){
		return name;
	}
	
	public EventContact getContactInfo(){
		return contact;
	}
}
