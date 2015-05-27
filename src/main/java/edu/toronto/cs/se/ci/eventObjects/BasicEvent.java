package edu.toronto.cs.se.ci.eventObjects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class BasicEvent extends Event {

	protected ArrayList<String> guests;
	protected Venue venue;
	protected String organizer;
	protected String title;
	protected String description;
	
	static public ArrayList<BasicEvent> loadFromJsonFile(File input) throws Exception{
		
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(input));
		
		//parse the json
		JsonParser parser = new JsonParser();
		JsonElement parsedJson = parser.parse(reader);
		if (!parsedJson.isJsonObject()){
			throw new Exception("Json events could not be found.");
		}
		JsonObject json = parsedJson.getAsJsonObject();
		JsonArray jsonEvents = (JsonArray) json.get("events");
		
		//create each event object from the json
		ArrayList<BasicEvent> events = new ArrayList<BasicEvent>(jsonEvents.size());
		Gson jsonToObject = new Gson();
		for (int i = 0; i< jsonEvents.size(); i++){
			events.add(jsonToObject.fromJson(jsonEvents.get(i), BasicEvent.class));
		}
		
		return events;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BasicEvent(){
		guests = new ArrayList<String>();
	}
	
	public BasicEvent(Venue venue, Collection<String> guestlist, String organizer){
		this.venue = venue;
		this.guests = (ArrayList<String>) guestlist;
		this.organizer = organizer;
	}
	
	public void setVenue(Venue v){
		venue = v;
	}
	
	public void setOrganizer(String o){
		organizer = o;
	}
	
	public void addGuest(String g){
		guests.add(g);
	}

	public ArrayList<String> getGuestNames() {
		return guests;
	}

	public Venue getVenue() {
		return venue;
	}

	public String getOrganizerName() {
		return organizer;
	}

}
