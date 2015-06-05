package edu.toronto.cs.se.ci.mains;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.eventSources.AreaCodeValid;
import edu.toronto.cs.se.ci.eventSources.CheckOrganizerFB;
import edu.toronto.cs.se.ci.eventSources.ClassifyingSource;
import edu.toronto.cs.se.ci.eventSources.EmailNameCooccurence;
import edu.toronto.cs.se.ci.eventSources.EventSource;
import edu.toronto.cs.se.ci.eventSources.GoogleMapsVenueAddress;
import edu.toronto.cs.se.ci.eventSources.OrganizerFaceBookExists;
import edu.toronto.cs.se.ci.eventSources.OrganizerWebSiteExists;
import edu.toronto.cs.se.ci.eventSources.TimeIsInPlausibleRange;
import edu.toronto.cs.se.ci.eventSources.TitleMatchesDescription;
import edu.toronto.cs.se.ci.eventSources.TwitterHandleVerified;
import edu.toronto.cs.se.ci.invokers.EventSourceInvoker;
import edu.toronto.cs.se.ci.sources.SourceFactory;

/**
 * Demo of loading events from a file, invoking sources on
 * them, and saving the results to a file.
 */
public class demo {

	static private String fileRealEvents = "./data/event data/chillwall.json";
	static private String fileFakeEvents = "./data/event data/random_english_events.json";
	//static private String fileFakeEvents = "./data/event data/fully_scrambled_chillwall.json";
	
	static private String outFilePath = "./data/chillwall-versus-random-english-1.2.arff";
	static private String logFilePath = "./log.txt";
	
	public static void main(String [] args) throws IOException{
		
		//load the real events
		Event [] realEvents;
		File inFile = new File(fileRealEvents);
		try{
			realEvents = Event.loadFromJsonFile(inFile);
		}
		catch (Exception e){
			e.printStackTrace();
			return;
		}
		
		//load the fake events
		Event [] fakeEvents;
		inFile = new File(fileFakeEvents);
		try{
			fakeEvents = Event.loadFromJsonFile(inFile);
		}
		catch (Exception e){
			e.printStackTrace();
			return;
		}
				
		//create the sources
		ArrayList<EventSource> sources = new ArrayList<EventSource>();
		//classify the events with a classifying source
		ClassifyingSource c = new ClassifyingSource("real event");
		for (int i = 0; i < realEvents.length; i++){
			c.classify(realEvents[i], 1);
		}
		for (int i = 0; i < fakeEvents.length; i++){
			c.classify(fakeEvents[i], 0);
		}
		
		//add all the sources
		sources.add(c);
		sources.add((GoogleMapsVenueAddress)SourceFactory.getSource(GoogleMapsVenueAddress.class));
		sources.add((CheckOrganizerFB)SourceFactory.getSource(CheckOrganizerFB.class));
		//sources.add((CheckOrganizerFBExact)SourceFactory.getSource(CheckOrganizerFBExact.class));
		sources.add((OrganizerWebSiteExists)SourceFactory.getSource(OrganizerWebSiteExists.class));
		//the URL they supplied, not a search for their name on facebook
		sources.add((OrganizerFaceBookExists)SourceFactory.getSource(OrganizerFaceBookExists.class));
		sources.add((EmailNameCooccurence)SourceFactory.getSource(EmailNameCooccurence.class));
		sources.add((AreaCodeValid)SourceFactory.getSource(AreaCodeValid.class));
		sources.add((TwitterHandleVerified)SourceFactory.getSource(TwitterHandleVerified.class));
		sources.add((TitleMatchesDescription)SourceFactory.getSource(TitleMatchesDescription.class));
		sources.add((TimeIsInPlausibleRange)SourceFactory.getSource(TimeIsInPlausibleRange.class));

		
		//let's have the log printed to a log.txt file
		FileWriter logWriter = new FileWriter(logFilePath);
		logWriter.write("demo.java\n\n");
		EventSource.setLogWriter(logWriter);
		
		//get a single list of events to invoke on
		ArrayList<Event> events = new ArrayList<Event>();
		for (int i = 0; i < realEvents.length; i++){
			events.add(realEvents[i]);
		}
		for (int i = 0; i < fakeEvents.length; i++){
			events.add(fakeEvents[i]);
		}
		
		//invoke the sources
		EventSourceInvoker invoker = new EventSourceInvoker("Event Plausibility", sources, events);
		System.out.println("invoking sources ...\n");
		invoker.invoke();
		
		//close sources to save their cache
		for (int i = 0; i < sources.size(); i++){
			sources.get(i).close();
		}	
		
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
