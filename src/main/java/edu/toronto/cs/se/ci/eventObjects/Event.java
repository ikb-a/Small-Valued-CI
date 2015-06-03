package edu.toronto.cs.se.ci.eventObjects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Event {

	private String title;
	private String url;
	private String description;
	private EventTime time;
	private EventOrganizer organizer;
	private Venue venue;
	
	/**
	 * Reads a file of events and returns them as an array java objects.
	 * @param input - A file in Json format with one Json array called "events",
	 * 				and where the events are compatible with the Event type.
	 * @return - An array of all Event objects which could be loaded from the file
	 * @throws FileNotFoundException 
	 */
	static public Event [] loadFromJsonFile(File input) throws FileNotFoundException{
		
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(input));
		
		//parse the json
		JsonParser parser = new JsonParser();
		JsonElement parsedJson = parser.parse(reader);
		JsonObject json = parsedJson.getAsJsonObject();
		JsonArray jsonEvents = (JsonArray) json.get("events");
		
		//create each event object from the json
		Event [] events = new Event [jsonEvents.size()];
		Gson jsonToObject = new Gson();
		for (int i = 0; i< jsonEvents.size(); i++){
			events[i] = jsonToObject.fromJson(jsonEvents.get(i), Event.class);
		}
		
		return events;
	}
	
	public Event(){
		title = "";
		url = "";
		description = "";
		time = null;
		organizer = null;
		venue = null;
	}
	
	public Event(String title, String url, String description, Venue venue, EventTime time, EventOrganizer organizer){
		this.title = title;
		this.url = url;
		this.description = description;
		this.venue = venue;
		this.time = time;
		this.organizer = organizer;
	}
	
	public String toString(){
		return getTitle();
	}
	
	/**
	 * Returns a string to uniquely identify the event.
	 * (Currently the event's title)
	 */
	public String getID(){
		return getTitle();
	}
	
	public String getSynopsis(){
		return String.format("\"%s\"\n%s - %s\n%s - %s\n	%s...\n",
				getTitle(),
				getStartTime(),
				getEndTime(),
				getVenueName(),
				getVenueAddress(),
				getTruncatedDescription(70));
	}
	
	public String getTruncatedDescription(int characterLimit){
		if (characterLimit > description.length()){
			characterLimit = description.length();
		}
		return description.substring(0, characterLimit);
	}
	
	public String getVenueName(){
		if (venue == null){
			return "";
		}
		return venue.getName();
	}
	
	public String getVenueAddress(){
		return venue.getTruncatedAddress();
	}
	
	/**
	 * The start time and date returned as one string
	 * according to the pattern in the EventTime's parser.
	 */
	public String getStartTime(){
		if (time == null){
			return "";
		}
		if(time.getStartDate() == null){
			return "";
		}
		return time.getStartDate().toString();
	}
	
	/**
	 * The end time and date returned as one string
	 * according to the pattern in the EventTime's parser.
	 */
	public String getEndTime(){
		if (time == null){
			return "";
		}
		if(time.getEndDate() == null){
			return "";
		}
		return time.getEndDate().toString();
	}
	
	public String getTitle() {
		return title;
	}
	public String getUrl() {
		return url;
	}
	public String getDescription() {
		return description;
	}
	public EventTime getTime() {
		return time;
	}
	public EventOrganizer getOrganizer() {
		return organizer;
	}
	public Venue getVenue(){
		return venue;
	}
}
