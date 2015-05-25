package edu.toronto.cs.se.ci.mains;

import java.util.ArrayList;
import java.util.Arrays;

import edu.toronto.cs.se.ci.invokers.EventSourceInvoker;
import edu.toronto.cs.se.ci.playground.data.Address;
import edu.toronto.cs.se.ci.eventObjects.BasicEvent;
import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.eventObjects.Venue;
import edu.toronto.cs.se.ci.eventSources.CheckGuestListFB;
import edu.toronto.cs.se.ci.eventSources.CheckGuestListNonFictional;
import edu.toronto.cs.se.ci.eventSources.EventSource;
import edu.toronto.cs.se.ci.eventSources.GoogleMapsVenueAddress;

public class invocation {

	public static void main(String[] args) {

		//make some events
		Address a1 = new Address("40", "St George Street", "Toronto", "Ontario", "Canada", "M5S 2E4");
		Venue v1 = new Venue("Bahen Centre for Information Technology", a1, null);
		ArrayList<String> guests1 = new ArrayList<String>(3);
		guests1.add("William Ginsberg");
		guests1.add("Marsha Chechik");
		guests1.add("Rick Salay");
		Event e1 = new BasicEvent(v1, guests1, null);
		
		Address a2 = new Address("51", "Baldwin St", "Toronto", "ON", "Canada", "M5T 1L1");
		Venue v2 = new Venue("Kinton Ramen", a2, null);
		ArrayList<String> guests2 = new ArrayList<String>(3);
		guests1.add("Taylor Stinson");
		guests1.add("Carina Conceicao");
		guests1.add("Sharon Fong");
		Event e2 = new BasicEvent(v2, guests2, null);
		
		ArrayList<Event> events = new ArrayList<Event>(2);
		events.add(e1);
		events.add(e2);
		
		//make some sources
		ArrayList<EventSource> sources = new ArrayList<EventSource>();
		sources.add(new CheckGuestListFB());
		sources.add(new GoogleMapsVenueAddress());
		sources.add(new CheckGuestListNonFictional());
		
		//do the invocation
		EventSourceInvoker invoker = new EventSourceInvoker(sources, null);
		invoker.invoke(sources, events);
		
		System.out.println(invoker.getFormattedResults());
	}

}
