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
public class EventSourceInvoker {

	private ArrayList<EventSource> sources;
	private ArrayList<Event> events;
	
	private int [][] results;
	
	/**
	 * Create a new EventSourceInvoker with no initial
	 * sources or events.
	 */
	public EventSourceInvoker(){
		sources = new ArrayList<EventSource>();
	}
	
	/**
	 * Create a new EventSourceInvoker with initial events and sources
	 * @param sources - possibly null collection of event sources
	 * @param events - possibly null collection of events
	 */
	public EventSourceInvoker(Collection<EventSource> sources, Collection<Event> events){
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
	 * Invoke a list of sources on a list of events
	 */
	public void invoke(List<EventSource> sources, List<Event> events){
		
		//create a 2d array or results where rows are events and columns are sources 
		results = new int [events.size()][sources.size()];
		
		//invoke the sources on each event
		for (int i = 0; i < events.size(); i++){
			results[i] = invoke(sources, events.get(i));
		}
		
	}
	
	/**
	 * Invoke sources on a single event
	 * @param e - Event object
	 */
	public int [] invoke(List<EventSource> sources, Event e){
		
		int [] sourceResponses = new int [sources.size()];
		for (int i = 0; i < sourceResponses.length; i++){
			try{
				sourceResponses[i] = sources.get(i).getResponse(e);
			}
			catch (Exception ex){
				sourceResponses[i] = -1;
			}
		}
		return sourceResponses;
		
	}
	
	/**
	 * Invoke sources on a single event with pre-set sources
	 * @param e - Event object
	 */
	public int [] invoke(Event e){
		
		return invoke(sources, e);
		
	}
	
	public int [][] getResults(){
		return results;
	}
	
	public boolean addSource(EventSource source){
		return sources.add(source);
	}
	
	public boolean removeSource(EventSource source){
		return sources.remove(source);
	}
	
	public void setEvents(Collection<Event> events){
		this.events = (ArrayList<Event>) events;
	}
	
	/**
	 * This method is used when an invocation was done
	 * which results in a 2d array. The sources are separated
	 * by commas and the events are separated by newlines.
	 */
	public String getFormattedResults(){
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < results.length; i++){
			for (int j = 0; j < results[0].length; j++){
				sb.append(results[i][j]);
				if (j + 1 < results[0].length){
					sb.append(",");
				}
			}
			if (i + 1 < results.length){
				sb.append("\n");
			}
		}
		
		return sb.toString();
		
	}
	
}
