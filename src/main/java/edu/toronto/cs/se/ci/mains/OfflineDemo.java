package edu.toronto.cs.se.ci.mains;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.eventSources.CheckOrganizerFB;
import edu.toronto.cs.se.ci.eventSources.EventSource;
import edu.toronto.cs.se.ci.eventSources.GoogleMapsVenueAddress;
import edu.toronto.cs.se.ci.invokers.EventSourceInvoker;

public class OfflineDemo {

	/**
	 * Run some sources offline to see if they generate the right files.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String [] args) throws Exception{
		
		File inFile = new File("./data/events.json");
		Event [] events = Event.loadFromJsonFile(inFile);
		
		//create some sources
		ArrayList<EventSource> sources = new ArrayList<EventSource>();
		sources.add(new GoogleMapsVenueAddress());
		sources.add(new CheckOrganizerFB());
		
		//make sure all sources are caching and online
		for (int i = 0; i < sources.size(); i++){
			sources.get(i).startRuntimeCaching();
			sources.get(i).goOnline();
		}
		
		//invoke the sources on the events
		EventSourceInvoker invoker = new EventSourceInvoker("Offline invocation test", sources, Arrays.asList(events));
		invoker.invoke();
		System.out.printf("Invocation online : \n%s\n", invoker.getFormattedResults());
		
		//close the sources to save their cache
		//and set them all as offline
		for (int i = 0; i < sources.size(); i++){
			sources.get(i).close();
			sources.get(i).goOffline();
		}
		
		//invoke again
		invoker.invoke();
		System.out.printf("Invocation offline with caching : \n%s\n", invoker.getFormattedResults());
		
		
	}
	
}
