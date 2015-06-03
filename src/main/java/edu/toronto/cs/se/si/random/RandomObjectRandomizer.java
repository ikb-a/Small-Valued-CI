package edu.toronto.cs.se.si.random;

import edu.toronto.cs.se.ci.eventObjects.Address;
import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.eventObjects.EventContact;
import edu.toronto.cs.se.ci.eventObjects.EventOrganizer;
import edu.toronto.cs.se.ci.eventObjects.EventTime;
import edu.toronto.cs.se.ci.eventObjects.Venue;

/**
 * Randomizes all fields in an object with random strings.
 * @author wginsberg
 *
 */
public class RandomObjectRandomizer extends EventObjectRandomizer{


	public Event event(Event e){
		
		if (e == null) e = new Event();
		
		e.setDescription(r.string(140, 500));
		e.setTitle(r.string(10, 30));
		e.setUrl(r.website());
		
		e.setOrganizer(organizer());
		e.setVenue(venue());
		e.setTime(time());
		
		return e;
	}
	
	public EventOrganizer organizer(EventOrganizer o){
		
		if (o == null) o = new EventOrganizer();
		
		o.setName(r.string(7, 20));
		
		o.setContact(contact(o.getContactInfo()));
		
		return o;
	}

	@Override
	public EventContact contact(EventContact c) {
		
		if (c == null) c = new EventContact();
		
		c.setEmail(r.emailAddress());
		c.setFacebookUrl(r.website());
		c.setPhone(r.phoneNumber());
		c.setTwitterHandle("@" + r.string(2, 12));
		c.setTwitterUrl(r.website());
		c.setWebsite(r.website());
		
		return c;
	}

	@Override
	public Venue venue(Venue v) {
		
		if (v == null) v = new Venue();
		
		v.setName(r.string(7, 30));
		
		v.setAddress(address(v.getAddress()));
		
		return v;
	}

	@Override
	public Address address(Address a) {
		
		if (a == null) a = new Address();
		
		a.setCity(r.string(4, 12));
		a.setCountry(r.string(4, 16));
		a.setPostalCode(r.postalCode());
		a.setProvince(r.string(3,  25));
		a.setRoute(r.string(6,  20));
		a.setStreetNumber(String.valueOf(r.integer(1, Integer.MAX_VALUE)));
		
		return a;
	}

	@Override
	public EventTime time(EventTime t) {
		
		if (t == null) t = new EventTime();
		
		t.getStartDate().setTime(r.longNumber());
		t.getEndDate().setTime(r.longNumber());
		
		return t;
	}
}
