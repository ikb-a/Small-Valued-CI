package edu.toronto.cs.se.ci.invokers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.toronto.cs.se.ci.eventSources.EventSource;
import edu.toronto.cs.se.ci.eventObjects.*;

/**
 * A class which can invoke a set of EventSources on
 * a set of event objects in order to get opinion
 * data on a set of events.
 * @author wginsberg
 *
 */
public class EventSourceInvoker extends Invoker<EventSource, Event, Integer> {

	private ArrayList<Event> events;
	
	/**
	 * Create a new EventSourceInvoker with initial events and sources
	 * @param sources - possibly null collection of event sources
	 * @param events - possibly null collection of events
	 */
	public EventSourceInvoker(String name, List<EventSource> sources, List<Event> events){
		super(name);
		if (sources != null){
			this.sources = (ArrayList<EventSource>) sources;
		}
		if (events != null){
			this.events = (ArrayList<Event>)events;			
		}
	}

	/**
	 * Invoke the sources which have been set in this instance
	 */
	public void invoke(){
		invoke(sources, events);
	}
		
	/**
	 * Invoke sources on a single event with pre-set sources
	 * @param e - Event object
	 */
	public Integer [] invoke(Event e){
		
		return invoke(sources, e);
		
	}
	
	
	public void setEvents(Collection<Event> events){
		this.events = (ArrayList<Event>) events;
	}
	

	/**
	 * Returns the string "{-1, 0, 1}"
	 */
	@Override
	protected String getSourceArffType() {
		/*
		 * We are using a ternary opinion system
		 * where True, False, Unknown are 1, 0, -1, respectively
		 */
		return "{-1, 0, 1}";
	}
	
}
