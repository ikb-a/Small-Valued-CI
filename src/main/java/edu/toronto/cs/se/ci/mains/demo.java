package edu.toronto.cs.se.ci.mains;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.eventObjects.BasicEvent;
import edu.toronto.cs.se.ci.eventSources.CheckGuestListFB;
import edu.toronto.cs.se.ci.eventSources.CheckGuestListNonFictional;
import edu.toronto.cs.se.ci.eventSources.CheckOrganizerFB;
import edu.toronto.cs.se.ci.eventSources.ClassifyingSource;
import edu.toronto.cs.se.ci.eventSources.EventSource;
import edu.toronto.cs.se.ci.eventSources.GoogleMapsVenueAddress;
import edu.toronto.cs.se.ci.invokers.EventSourceInvoker;

public class demo {

	static private String fileRealEvents = "./data/real_events.json";
	static private String fileFakeEvents = "./data/fake_events.json";
	static private String outFilePath = "./data/plausible event.arff";
	
	public static void main(String [] args){
		
		//load the real events
		ArrayList<BasicEvent> realEvents = new ArrayList<BasicEvent>();
		File inFile = new File(fileRealEvents);
		try{
			realEvents = BasicEvent.loadFromJsonFile(inFile);
		}
		catch (Exception e){
			e.printStackTrace();
			return;
		}
		
		//load the fake events
		ArrayList<BasicEvent> fakeEvents = new ArrayList<BasicEvent>();
		inFile = new File(fileFakeEvents);
		try{
			fakeEvents = BasicEvent.loadFromJsonFile(inFile);
		}
		catch (Exception e){
			e.printStackTrace();
			return;
		}
				
		//create the sources
		ArrayList<EventSource> sources = new ArrayList<EventSource>();
		//classify the events with a classifying source
		ClassifyingSource c = new ClassifyingSource("real event");
		for (int i = 0; i < realEvents.size(); i++){
			c.classify(realEvents.get(i), 1);
		}
		for (int i = 0; i < fakeEvents.size(); i++){
			c.classify(fakeEvents.get(i), 0);
		}
		
		//add all the sources
		sources.add(c);
		sources.add(new CheckGuestListFB());
		sources.add(new GoogleMapsVenueAddress());
		sources.add(new CheckOrganizerFB());
		sources.add(new CheckGuestListNonFictional());
		
		//get a single list of events to invoke on
		ArrayList<Event> events = new ArrayList<Event>();
		events.addAll(fakeEvents);
		events.addAll(realEvents);
		
		//invoke the sources
		EventSourceInvoker invoker = new EventSourceInvoker("Event Plausibility", sources, events);
		invoker.invoke();
		
		//save to a file
		try{
			File outFile = new File(outFilePath);
			String outFileComment = String.format("Real events : %s\nFake events : %s", fileRealEvents, fileFakeEvents);
			invoker.saveToArff(outFile, outFileComment);
			System.out.printf("Saved results to %s\n", outFilePath);
		}
		catch (IOException ex){
			System.out.println("Couldn't open the out file. Dumping to stdout instead:");
			System.out.println(invoker.getFormattedResults());
		}
		
	}
	
}