package edu.toronto.cs.se.ci.random;

import edu.toronto.cs.se.ci.eventObjects.Address;
import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.eventObjects.EventContact;
import edu.toronto.cs.se.ci.eventObjects.EventOrganizer;
import edu.toronto.cs.se.ci.eventObjects.EventTime;
import edu.toronto.cs.se.ci.eventObjects.Venue;

public abstract class EventObjectRandomizer {

	Randomization r;
	
	public EventObjectRandomizer(Randomization r){
		this.r = r;
	}
	
	public EventObjectRandomizer(){
		this.r = new Randomization();
	}
	
	abstract public Event event(Event e);
	public Event event(){
		return event(null);
	}
	
	abstract public EventOrganizer organizer(EventOrganizer o);
	public EventOrganizer organizer(){
		return organizer(null);
	}
	
	abstract public EventContact contact(EventContact c);
	public  EventContact contact(){
		return contact(null);
	}
	
	abstract public Venue venue(Venue v);
	public Venue venue(){
		return venue(null);
	}
	
	abstract public Address address(Address a);
	public Address address(){
		return address(null);
	}
	
	abstract public EventTime time(EventTime t);
	public EventTime time(){
		return time(null);
	}
}
